package PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto;


import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.AppointmentSche;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentScheDTO {

    private Integer id;
    private Integer specializationId;
    private Integer clinicId;
    private Integer doctorId;
    private Integer userId;
    private LocalDate date;
    private BigDecimal consultationCost;
    private String name;
    private LocalDateTime dateTime;
    private String gender;
    private String phone;
    private String address;
    private String note;
    private Integer status;
    private String reason;


    private String specializationName;
    private String specializationDescription;
    private String clinicName;
    private String clinicAddress;
    private String clinicPhone;
    private String doctorName;
    private String doctorEmail;
    private String userName;
    private String userEmail;
    // Constructor
    public AppointmentScheDTO(Integer id, Integer specializationId, Integer clinicId, Integer doctorId, Integer userId, LocalDate date,
                              BigDecimal consultationCost, String name, LocalDateTime dateTime, String gender, String phone,
                              String address, String note, Integer status, String reason, String specializationName,
                              String specializationDescription, String clinicName, String clinicAddress,
                              String clinicPhone, String doctorName, String doctorEmail, String userName, String userEmail) {
        this.id = id;
        this.specializationId = specializationId;
        this.clinicId = clinicId;
        this.doctorId = doctorId;
        this.userId = userId;
        this.date = date;
        this.consultationCost = consultationCost;
        this.name = name;
        this.dateTime = dateTime;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.status = status;
        this.reason = reason;
        this.specializationName = specializationName;
        this.specializationDescription = specializationDescription;
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.clinicPhone = clinicPhone;
        this.doctorName = doctorName;
        this.doctorEmail = doctorEmail;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public String getSpecializationDescription() {
        return specializationDescription;
    }

    public void setSpecializationDescription(String specializationDescription) {
        this.specializationDescription = specializationDescription;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public String getClinicPhone() {
        return clinicPhone;
    }

    public void setClinicPhone(String clinicPhone) {
        this.clinicPhone = clinicPhone;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public AppointmentScheDTO(Integer id, Integer specializationId, Integer userId, Integer doctorId, Integer clinicId, LocalDate date, BigDecimal consultationCost, String name, LocalDateTime dateTime, String gender, String phone, String address, String note, Integer status, String reason) {
        this.id = id;
        this.specializationId = specializationId;
        this.userId = userId;
        this.doctorId = doctorId;
        this.clinicId = clinicId;
        this.date = date;
        this.consultationCost = consultationCost;
        this.name = name;
        this.dateTime = dateTime;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.status = status;
        this.reason = reason;
    }

    public AppointmentScheDTO() {
    }

    public AppointmentScheDTO(AppointmentSche appointment) {
        if (appointment != null) {
            this.id = appointment.getId();
            this.specializationId = appointment.getSpecialization().getId();
            this.clinicId = Math.toIntExact(appointment.getClinic().getId());
            this.doctorId = Math.toIntExact(appointment.getDoctor().getId());
            this.userId = Math.toIntExact(appointment.getUser().getId());
            this.date = LocalDate.ofEpochDay(appointment.getDate().toEpochDay());
            this.consultationCost = appointment.getConsultationCost();
            this.name = appointment.getUser().getName();
            this.dateTime = appointment.getDate().atStartOfDay();
            this.gender = appointment.getUser().getGender();
            this.phone = appointment.getUser().getPhone();
            this.address = appointment.getUser().getAddress();
            this.note = appointment.getNote();
            this.status = appointment.getStatus().getId();
            this.reason = appointment.getReason();
        }
    }


//    public AppointmentScheDTO(Integer id, Integer specializationId, Integer clinicId, Integer userId, LocalDate date, BigDecimal consultationCost, String name, String gender, String phone, String address, String note, LocalDateTime dateTime, Status status) {
//    }
// Getters and Setters

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getConsultationCost() {
        return consultationCost;
    }

    public void setConsultationCost(BigDecimal consultationCost) {
        this.consultationCost = consultationCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
