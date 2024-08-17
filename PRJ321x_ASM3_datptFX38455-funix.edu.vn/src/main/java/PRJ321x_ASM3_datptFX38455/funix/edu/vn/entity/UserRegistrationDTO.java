//package PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//
//
//public class UserRegistrationDTO {
//    @NotBlank(message = "Tên không được để trống")
//    private String name;
//
//    private String gender;
//
//    @NotBlank(message = "Email không được để trống")
//    @Email(message = "Email không hợp lệ")
//    private String email;
//
//    @NotBlank(message = "Số điện thoại không được để trống")
//    private String phone;
//
//    @NotBlank(message = "Địa chỉ không được để trống")
//    private String address;
//
//    @NotBlank(message = "Mật khẩu không được để trống")
//    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
//    private String password;
//
//    @NotBlank(message = "Xác nhận mật khẩu không được để trống")
//    private String confirmPassword;
//
//    // Getters and Setters
//
//    public @NotBlank(message = "Tên không được để trống") String getName() {
//        return name;
//    }
//
//    public void setName(@NotBlank(message = "Tên không được để trống") String name) {
//        this.name = name;
//    }
//
//    public @NotBlank(message = "Xác nhận mật khẩu không được để trống") String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public void setConfirmPassword(@NotBlank(message = "Xác nhận mật khẩu không được để trống") String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }
//
//    public @NotBlank(message = "Mật khẩu không được để trống") @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự") String getPassword() {
//        return password;
//    }
//
//    public void setPassword(@NotBlank(message = "Mật khẩu không được để trống") @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự") String password) {
//        this.password = password;
//    }
//
//    public @NotBlank(message = "Địa chỉ không được để trống") String getAddress() {
//        return address;
//    }
//
//    public void setAddress(@NotBlank(message = "Địa chỉ không được để trống") String address) {
//        this.address = address;
//    }
//
//    public @NotBlank(message = "Số điện thoại không được để trống") String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(@NotBlank(message = "Số điện thoại không được để trống") String phone) {
//        this.phone = phone;
//    }
//
//    public @NotBlank(message = "Email không được để trống") @Email(message = "Email không hợp lệ") String getEmail() {
//        return email;
//    }
//
//    public void setEmail(@NotBlank(message = "Email không được để trống") @Email(message = "Email không hợp lệ") String email) {
//        this.email = email;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//}
