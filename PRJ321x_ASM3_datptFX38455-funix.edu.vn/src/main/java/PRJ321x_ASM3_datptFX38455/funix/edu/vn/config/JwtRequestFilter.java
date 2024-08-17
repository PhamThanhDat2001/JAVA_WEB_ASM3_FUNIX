package PRJ321x_ASM3_datptFX38455.funix.edu.vn.config;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        // Lấy token từ tham số URL
//        String jwtToken = request.getParameter("token");
//
////        System.out.println("abc" + jwtToken);
//
//        if (jwtToken == null) {
//            // Token không được tìm thấy, ghi log v à tiếp tục với các bộ lọc còn lại
//
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String username = null;
//
//        try {
//            // Lấy username từ token
//            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
////            System.out.println("usnam" + username);
//        } catch (IllegalArgumentException e) {
//            logger.error("Unable to get JWT Token", e);
//            chain.doFilter(request, response);
//            return;
//        } catch (ExpiredJwtException e) {
//            logger.error("JWT Token has expired", e);
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // Kiểm tra xem username có tồn tại và nếu không có authentication hiện tại thì tiến hành xác thực
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
//
//            // Nếu token hợp lệ, cấu hình xác thực cho Spring Security
//            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                // Thiết lập xác thực vào SecurityContext
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//
//        // Tiếp tục với các bộ lọc còn lại
//        chain.doFilter(request, response);
//    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7); // Lấy token mà không có tiền tố "Bearer"
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }



}
