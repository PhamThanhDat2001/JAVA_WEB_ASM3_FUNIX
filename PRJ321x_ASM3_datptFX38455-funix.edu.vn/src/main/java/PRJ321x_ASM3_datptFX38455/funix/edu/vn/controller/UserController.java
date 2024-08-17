package PRJ321x_ASM3_datptFX38455.funix.edu.vn.controller;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.config.JwtTokenUtil;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Specialization;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.User;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.service.SpecializationService;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SpecializationService specializationService;

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        String jwtToken = request.getParameter("token");

        if (jwtToken != null) {
            try {
                String email = jwtTokenUtil.getUsernameFromToken(jwtToken);
                model.addAttribute("token", jwtToken);
                model.addAttribute("email", email);
            } catch (Exception e) {
                model.addAttribute("error", "Invalid token.");
            }
        } else {
            model.addAttribute("error", "No token found.");
        }

        return "home";
    }


    @GetMapping("/user/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid credentials. Please try again.");
        }
        return "login";  // Trả về tên của JSP/HTML đăng nhập
    }

//    @GetMapping("/user/logout")
//    public void logout(HttpServletResponse response, HttpServletRequest request) throws IOException {
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", 0);
//
//        // Invalidate the session
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//
//        // Redirect to login page with logout message
//        response.sendRedirect("/api/v1/user/login?logout=true");
//    }
    @GetMapping("/user/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm(Model model) {
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String resetPassword(Model model) {
        return "new-password"; // Tên của trang Thymeleaf
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "profile"; // Tên của trang Thymeleaf
    }

    @GetMapping("/schedule/spec")
    public String schedule(Model model) {
        return "schedule-specialization"; // Tên của trang Thymeleaf
    }
    @GetMapping("/schedule/appointment")
    public String appointment(Model model) {
        return "schedule-appointment"; // Tên của trang Thymeleaf
    }
    @GetMapping("/clinic")
    public String clinic(Model model) {
        return "clinic-details"; // Tên của trang Thymeleaf
    }

    @GetMapping("/doctor")
    public String doctor(Model model) {

        return "doctor";
    }
    @GetMapping("/user/sche/admin")
    public String sche(Model model) {

        return "user-sche-admin";
    }
    @GetMapping("/doctor/sche/admin")
    public String schee(Model model) {

        return "doctor-sche-admin";
    }
    @GetMapping("/admin")
    public String redirectToCheckAccess() {
        // Chuyển hướng đến trang kiểm tra quyền truy cập
        return "admin";
    }
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied"; // Returns the access-denied.html page
    }
    @GetMapping("/check-access")
    public String showCheckAccessPage() {
        // Trả về trang kiểm tra quyền truy cập
        return "check-access";
    }
}
