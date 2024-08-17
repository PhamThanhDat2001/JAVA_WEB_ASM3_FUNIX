package PRJ321x_ASM3_datptFX38455.funix.edu.vn.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendPasswordResetEmail(String email, String token) throws MessagingException {
        // Xây dựng URL đặt lại mật khẩu với token
        String resetUrl = token;

        // Tạo đối tượng Context để truyền dữ liệu vào mẫu
        Context context = new Context();
        context.setVariable("resetUrl", resetUrl);

        // Xử lý mẫu Thymeleaf để tạo nội dung email
        String content = templateEngine.process("password-reset-email", context);

        // Tạo và cấu hình đối tượng MimeMessage
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email); // Địa chỉ email người nhận
        helper.setSubject("Đặt lại mật khẩu của bạn"); // Tiêu đề email
        helper.setText(content, true); // Nội dung email dưới dạng HTML

        // Gửi email
        mailSender.send(message);
    }

}
