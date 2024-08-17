package PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto;


import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.AppointmentSche;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.User;
import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String gender;
    private String avatar;
    private String description;
    private List<AppointmentScheDTO> appointments;
    private SpecializationDTO specializationDTO;


    private String namextraInfose;
    private Long clinicId;
    private Boolean isActive;

    private String trainingProcess;
    private String achievement;
    private String reason;


    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public UserDTO() {}


    public UserDTO(User user, List<AppointmentSche> appointments) {
        if (user != null) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.address = user.getAddress();
            this.phone = user.getPhone();
            this.gender = user.getGender();
            this.avatar = user.getAvatar();
            this.description = user.getDescription();
            this.isActive = user.getActive(); // Cập nhật isActive


            this.trainingProcess = user.getTrainingProcess();
            this.achievement = user.getAchievement();
            this.reason = user.getReason();
        }
        this.appointments = appointments != null ? appointments.stream()
                .map(AppointmentScheDTO::new)
                .collect(Collectors.toList()) : new ArrayList<>();
    }

    // Getters and setters...




    public UserDTO(Long id, String name, String email, String address, String phone, String avatar, String description, SpecializationDTO specializationDTO, String namextraInfose, Long clinicId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.description = description;
        this.specializationDTO = specializationDTO;
        this.namextraInfose = namextraInfose;
        this.clinicId = clinicId;


    }
    public UserDTO(Long id, String name, String email, String address, String phone, String avatar, String description, SpecializationDTO specializationDTO, String namextraInfose, Long clinicId, String trainingProcess, String achievement,String reason, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.description = description;
        this.specializationDTO = specializationDTO;
        this.namextraInfose = namextraInfose;
        this.clinicId = clinicId;
        this.trainingProcess = trainingProcess;
        this.achievement = achievement;
        this.reason = reason;
        this.isActive = isActive;

    }

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

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public String getNamextraInfose() {
        return namextraInfose;
    }

    public void setNamextraInfose(String namextraInfose) {
        this.namextraInfose = namextraInfose;
    }

    public UserDTO(String name) {
        this.name = name;
    }

    public SpecializationDTO getSpecializationDTO() {
        return specializationDTO;
    }

    public void setSpecializationDTO(SpecializationDTO specializationDTO) {
        this.specializationDTO = specializationDTO;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<AppointmentScheDTO> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentScheDTO> appointments) {
        this.appointments = appointments;
    }
}
