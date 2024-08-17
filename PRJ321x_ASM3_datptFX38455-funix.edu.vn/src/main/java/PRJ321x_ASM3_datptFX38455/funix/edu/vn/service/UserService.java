package PRJ321x_ASM3_datptFX38455.funix.edu.vn.service;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.config.JwtTokenUtil;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.RoleDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.UserDTO;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Role;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.User;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository.RoleRepository;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }


    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    public void resetPassword(String token, String newPassword) {
        User user = findUserByToken(token);
            System.out.println("tokendumaaa: " + token);
        System.out.println("dumaaaa: " + user);
        if (user != null) {
            // Cập nhật mật khẩu
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new RuntimeException("Người dùng không tìm thấy hoặc token không hợp lệ.");
        }
    }




    private User findUserByToken(String token) {
        // Tìm người dùng theo token

        return userRepository.findByResetToken(token); // Thay thế bằng người dùng thực tế
    }

    public void updateResetToken(String email, String token) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Cập nhật chỉ trường resetToken
            user.setResetToken(token);

            // Lưu đối tượng User với trường resetToken được cập nhật
            userRepository.updateResetToken(email, token);
        } else {
            throw new RuntimeException("Người dùng không tồn tại.");
        }
    }
//
//    public void createAndSendResetToken(String email) {
//        // Tạo token mới
//        String token = jwtTokenUtil.generateToken(email);
//
//        // Cập nhật token vào người dùng
//        updateResetToken(email, token);
//
//        // Gửi email với token
//        try {
//            emailService.sendPasswordResetEmail(email, token);
//        } catch (MessagingException e) {
//            // Xử lý lỗi gửi email
//        }
//    }

    public void createAndSendResetToken(String email) {
        // Lấy thông tin người dùng để lấy role
        User user = userRepository.findUserByEmail(email);
        String role = user.getRole().getName(); // Assuming getName() returns the role name, modify if necessary

        // Tạo token mới với role
        String token = jwtTokenUtil.generateToken(email, role);

        // Cập nhật token vào người dùng
        updateResetToken(email, token);

        // Gửi email với token
        try {
            emailService.sendPasswordResetEmail(email, token);
        } catch (MessagingException e) {
            // Xử lý lỗi gửi email
            e.printStackTrace(); // Log lỗi để dễ dàng phát hiện sự cố
        }
    }


    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
//    @Autowired
//    private AppointmentService appointmentService;
//
//    public UserDTO convertToDTO(User user) {
//        if (user == null) {
//            return null; // Trả về null nếu đối tượng User là null
//        }
//
//        // phương thức trong UserService để lấy danh sách các cuộc hẹn của người dùng
//        List<Appointment> appointments = appointmentService.getAppointmentsByUser(user.getId());
//
//        // Tạo đối tượng UserDTO từ User và danh sách Appointment
//        return new UserDTO(user, appointments);
//    }

    public Optional<UserDTO> getDoctorNameById(Integer id) {
        return userRepository.findById(id)
                .map(doctor -> new UserDTO(doctor.getName()));
    }

    public List<User> getUsersByRoleId(Long roleId) {
        return userRepository.findByRoleId(roleId);
    }
    public void updateUserStatus(Integer userId, boolean active) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(active);
        userRepository.save(user);
    }
//    public void toggleUserStatus(Integer userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        user.setActive(!user.getActive());
//        userRepository.save(user);
//    }
public User toggleUserStatus(Integer userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    boolean newStatus = !user.getActive();
    user.setActive(newStatus);
    userRepository.save(user);

    return user;
}

    public boolean cancelUser(Integer userId, String reason) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            // Đảo ngược trạng thái của người dùng
            boolean newStatus = !user.getActive(); // Nếu hiện tại là true thì đổi thành false, ngược lại
            user.setActive(newStatus);
            user.setReason(reason);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



//    public User createUser(UserDTO userDTO) {
//        User user = new User();
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setAddress(userDTO.getAddress());
//        user.setPhone(userDTO.getPhone());
//        user.setGender(userDTO.getGender());
//        user.setAvatar(userDTO.getAvatar());
//        user.setDescription(userDTO.getDescription());
//        user.setTrainingProcess(userDTO.getTrainingProcess());
//        user.setAchievement(userDTO.getAchievement());
//        user.setReason(userDTO.getReason());
//        user.setActive(userDTO.getActive());
//
//        // Đặt mật khẩu mặc định và mã hóa nó
//        String defaultPassword = "123456";
//        user.setPassword(passwordEncoder.encode(defaultPassword));
//
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());
//
//        return userRepository.save(user);
//    }

}
