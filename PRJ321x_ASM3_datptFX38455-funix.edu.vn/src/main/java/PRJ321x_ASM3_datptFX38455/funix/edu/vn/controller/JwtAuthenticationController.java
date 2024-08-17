package PRJ321x_ASM3_datptFX38455.funix.edu.vn.controller;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.config.*;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.DoctorUserDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.UserDTO;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.AppointmentSche;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.DoctorUser;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Role;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.User;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository.RoleRepository;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.service.EmailService;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @PostMapping("/api/v1/user/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu không khớp!");
        }

        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email đã tồn tại!");
        }

        try {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Role defaultRole = roleRepository.findById(4).orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));
            user.setRole(defaultRole);
            user.setActive(true);
            userService.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("Đăng ký thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

//@PostMapping("/authenticate")
//public ResponseEntity<?> createAuthenticationToken(@RequestParam String email, @RequestParam String password) {
//    try {
//        // Authenticate the user
//        authenticate(email, password);
//
//        // Load user details
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//
//        // Generate token
//        User user = userService.findUserByEmail(email);
//        String role = user.getRole().getName(); // Assuming getName() returns the role name, modify if necessary
//        String token = jwtTokenUtil.generateToken(userDetails.getUsername(), role);
//
//        Long userId = user.getId();
//        // Get authorities using the getAuthorities method
//            Collection<? extends GrantedAuthority> authorities = getAuthorities(user);
//
//            // Print authorities to console for debugging
//            System.out.println("Authorities: " + authorities);
////
//        // Return token in response
//        return ResponseEntity.ok().body(new TokenResponse(token, role, userId));
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
//    }
//}
@PostMapping("/authenticate")
public ResponseEntity<?> createAuthenticationToken(@RequestParam String email, @RequestParam String password) {
    try {
        // Xác thực người dùng
        authenticate(email, password);

        // Tải thông tin người dùng
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Sinh token
        User user = userService.findUserByEmail(email);
        String role = user.getRole().getName(); // Giả sử getName() trả về tên vai trò, thay đổi nếu cần
        String token = jwtTokenUtil.generateToken(userDetails.getUsername(), role);
        if (!user.getActive()) {
            throw new Exception("Tài khoản của bạn đã bị khóa");
        }
        Long userId = user.getId();
        // Lấy quyền hạn sử dụng phương thức getAuthorities
        Collection<? extends GrantedAuthority> authorities = getAuthorities(user);

        // Đẩy token vào header và trả về phản hồi
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new TokenResponse(token, role, userId));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
    }
}

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName())); // Sử dụng tên quyền hạn với tiền tố "ROLE_"
        return authorities;
    }


    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Sai thông tin tài khoản", e);
        }
    }

    @Autowired
    private EmailService emailService;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        try {

//            userService.sendPasswordResetEmail(email);
            userService.createAndSendResetToken(email);
            return ResponseEntity.ok("Đã gửi email xác nhận để đổi mật khẩu.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @GetMapping("/api/v1/user/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        // Invalidate the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Return a response entity with a status code and optional headers
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/api/v1/user/login?logout=true")
                .build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String token,
            @RequestParam String password,
            @RequestParam String confirmPassword) {
        System.out.println("aâ " + token);
        try {
            if (!password.equals(confirmPassword)) {
                return ResponseEntity.badRequest().body("Mật khẩu xác nhận không khớp.");
            }
            userService.resetPassword(token, password);
            return ResponseEntity.ok("Mật khẩu đã được đổi thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }


    @GetMapping("api/v1/user/profile")
    public ResponseEntity<Map<String, Object>> getUserProfile(@RequestParam String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<User> userOptional = userService.getUserByEmail(email);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                List<AppointmentSche> appointments = user.getAppointments();
                UserDTO userDTO = new UserDTO(user, appointments);

                response.put("status", "success");
                response.put("data", userDTO);

                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/api/v1/user/profile/update")
    public ResponseEntity<Map<String, Object>> updateUserProfile(@RequestBody UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Validate and update user profile
            Optional<User> userOptional = userService.getUserByEmail(userDTO.getEmail());

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setName(userDTO.getName());
                user.setAddress(userDTO.getAddress());
                user.setPhone(userDTO.getPhone());
                user.setGender(userDTO.getGender());

                // Save updated user
                userService.saveUser(user);

                response.put("status", "success");
                response.put("message", "Profile updated successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/api/v1/doctor/name/{doctorId}")
    public ResponseEntity<?> getDoctorNameById(@PathVariable Integer doctorId) {
        return userService.getDoctorNameById(doctorId)
                .map(doctorDTO -> ResponseEntity.ok().body(doctorDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


//    @GetMapping("/api/v1/users/by-role")
//    public List<UserDTO> getUsersByRole(@RequestParam Long roleId) {
//        List<User> users = userService.getUsersByRoleId(roleId);
//        // Chuyển đổi từ User thành UserDTO
//        return users.stream()
//                .map(user -> new UserDTO(user, user.getAppointments()))
//                .collect(Collectors.toList());
//    }
    @GetMapping("/api/v1/users/by-role")
    public List<UserDTO> getUsersByRole(@RequestParam Long roleId) {
        List<User> users = userService.getUsersByRoleId(roleId);
        // Chuyển đổi từ User thành UserDTO
        return users.stream()
                .map(user -> new UserDTO(user, user.getAppointments()))
                .collect(Collectors.toList());
    }

    @PutMapping("/api/v1/{userId}/active")
    public ResponseEntity<?> toggleUserStatus(@PathVariable Integer userId) {
        userService.toggleUserStatus(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/v1/users/{userId}/cancel")
    public ResponseEntity<?> cancelUser(@PathVariable Integer userId, @RequestParam String reason) {
        boolean success = userService.cancelUser(userId, reason);
        if (success) {
            return ResponseEntity.ok("User account status updated successfully");
        } else {
            return ResponseEntity.status(500).body("Failed to update user account status");
        }
    }



}
