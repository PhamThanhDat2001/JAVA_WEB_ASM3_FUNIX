package PRJ321x_ASM3_datptFX38455.funix.edu.vn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
//                                .requestMatchers("/api/v1/user/login").permitAll()
//                                .requestMatchers("/api/v1/home").hasRole("PATIENT")
//                                .requestMatchers("/api/v1/user/register").permitAll()
//                                .requestMatchers("/api/v1/clinics/search/**").permitAll()
//                                .requestMatchers("/api/v1/doctor/**").hasAuthority("ROLE_DOCTOR") // Đảm bảo rằng quyền hạn này đúng
//                                .anyRequest().permitAll()
//                )
//                .exceptionHandling(exceptionHandling ->
//                        exceptionHandling
//                                .accessDeniedHandler(accessDeniedHandler()) // Set access denied handler
//                )
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session management
//                )
//                .csrf(csrf -> csrf.disable()); // Disable CSRF for REST APIs
//
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()  // Thêm đường dẫn xử lý đăng nhập
                                .requestMatchers("/api/v1/user/login").permitAll()  // Thêm đường dẫn trả về trang login
                                .requestMatchers("/api/v1/home").permitAll()
                                .requestMatchers("/api/v1/user/register").permitAll()
                                .requestMatchers("/api/v1/clinics/search/**").permitAll()
//                                .requestMatchers("/api/v1/doctor/**").hasAuthority("ROLE_DOCTOR") // Đảm bảo quyền hạn là đúng
                                .requestMatchers("/api/v1/doctor/**").permitAll() // Đảm bảo quyền hạn là đúng

                                .anyRequest().permitAll()
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedHandler(accessDeniedHandler())
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //    @Bean
    //    public BCryptPasswordEncoder bpasswordEncoder() {
    //        return new BCryptPasswordEncoder();
    //    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/api/v1/access-denied");
        };
    }
}