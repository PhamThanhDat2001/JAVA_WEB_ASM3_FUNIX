    package PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity;
    
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonInclude;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    
    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.Collections;
    import java.util.List;
    
    @Entity
    @Table(name = "users")
    public class User  implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
    
        @NotBlank(message = "Tên không được để trống")
        @Column(name = "name", length = 255)
        private String name;
    
        @NotBlank(message = "Email không được để trống")
        @Email(message = "Email không hợp lệ")
        @Column(name = "email", length = 255, unique = true)
        private String email;
    
        @NotBlank(message = "Mật khẩu không được để trống")
        @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
        @Column(name = "password", length = 255)
        private String password;
    
        @NotBlank(message = "Địa chỉ không được để trống")
        @Column(name = "address", length = 255)
        private String address;
    
        @NotBlank(message = "Số điện thoại không được để trống")
        @Column(name = "phone", length = 255)
        private String phone;
    
        @Column(name = "avatar", length = 255)
        private String avatar;
    
        @Column(name = "gender", length = 255)
        private String gender;
    
        @Lob
        @Column(name = "description")
        private String description;
    
        @ManyToOne
        @JoinColumn(name = "role_id")
        @JsonIgnore // Loại bỏ vòng lặp vô tận
        private Role role;
    
        @Column(name = "is_active")
        private Boolean isActive= true;

        @Column(name = "created_at")
        private LocalDateTime createdAt;
    
        @Column(name = "updated_at")
        private LocalDateTime  updatedAt;
    
        @Column(name = "deleted_at")
        private LocalDateTime  deletedAt;
    
        @OneToMany(mappedBy = "doctor")
        private List<DoctorUser> doctorUsers;
    
    //    @OneToMany(mappedBy = "writer")
    //    private List<Post> posts;
    
        @OneToMany(mappedBy = "supporter")
        private List<SupporterLog> supporterLogs;
    
        @OneToMany(mappedBy = "user")
        private List<AppointmentSche> appointments;

        @Column(name = "training_process")
        private String trainingProcess;
        @Column(name = "achievement")
        private String achievement;
        @Column(name = "reason")
        private String reason;


        // Getters and Setters


        public String getTrainingProcess() {
            return trainingProcess;
        }

        public void setTrainingProcess(String trainingProcess) {
            this.trainingProcess = trainingProcess;
        }

        public String getAchievement() {
            return achievement;
        }

        public void setAchievement(String achievement) {
            this.achievement = achievement;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public List<AppointmentSche> getAppointments() {
            return appointments;
        }
    
        public void setAppointments(List<AppointmentSche> appointments) {
            this.appointments = appointments;
        }
    
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getEmail() {
            return email;
        }
    
        public void setEmail(String email) {
            this.email = email;
        }
    
        public String getPassword() {
            return password;
        }
    
        @Override
        public String getUsername() {
            return this.email;
        }
    
    
        @Override
        public boolean isAccountNonExpired() {
            return UserDetails.super.isAccountNonExpired();
        }
    
        @Override
        public boolean isAccountNonLocked() {
            return UserDetails.super.isAccountNonLocked();
        }
    
        @Override
        public boolean isCredentialsNonExpired() {
            return UserDetails.super.isCredentialsNonExpired();
        }
    
        @Override
        public boolean isEnabled() {
            return UserDetails.super.isEnabled();
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getAddress() {
            return address;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
    
        public String getAvatar() {
            return avatar;
        }
    
        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    
        public String getGender() {
            return gender;
        }
    
        public void setGender(String gender) {
            this.gender = gender;
        }
    
        public String getPhone() {
            return phone;
        }
    
        public void setPhone(String phone) {
            this.phone = phone;
        }
    
        public String getDescription() {
            return description;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        public Boolean getActive() {
            return isActive;
        }
    
        public void setActive(Boolean active) {
            isActive = active;
        }


        @JsonInclude(JsonInclude.Include.NON_NULL)
        public Role getRole() {
            return role;
        }
    
        public void setRole(Role role) {
            this.role = role;
        }
    
        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
    
        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }
    
        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }
    
        public void setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }
    
        public LocalDateTime getDeletedAt() {
            return deletedAt;
        }
    
        public void setDeletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
        }
    
        public List<DoctorUser> getDoctorUsers() {
            return doctorUsers;
        }

        public void setDoctorUsers(List<DoctorUser> doctorUsers) {
            this.doctorUsers = doctorUsers;
        }
    
    //    public List<Post> getPosts() {
    //        return posts;
    //    }
    //
    //    public void setPosts(List<Post> posts) {
    //        this.posts = posts;
    //    }
    
        public List<SupporterLog> getSupporterLogs() {
            return supporterLogs;
        }
    
        public void setSupporterLogs(List<SupporterLog> supporterLogs) {
            this.supporterLogs = supporterLogs;
        }
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(role.getName())); // Vai trò từ lớp Role
//            return authorities;
//        }
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
}
    //    @Override
    //    public Collection<? extends GrantedAuthority> getAuthorities() {
    //        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    //    }
    
        @Transient
        private String confirmPassword;
        @Column(name = "reset_token")
        private String resetToken;
    
        public String getConfirmPassword() {
            return confirmPassword;
        }
        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }
    
        public String getResetToken() {
            return resetToken;
        }
    
        public void setResetToken(String resetToken) {
            this.resetToken = resetToken;
        }
        @PrePersist
        protected void onCreate() {
            this.createdAt = LocalDateTime.now();
        }
    
    
        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
    
                    ", role=" + role.getName() +
    
                    '}';
        }
    }
