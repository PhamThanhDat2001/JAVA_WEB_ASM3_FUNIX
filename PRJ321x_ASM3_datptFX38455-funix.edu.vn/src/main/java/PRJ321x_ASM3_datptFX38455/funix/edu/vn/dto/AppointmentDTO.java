package PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Appointment;

import java.util.Date;

public class AppointmentDTO {
    private Long id;
    private String specializationName;
    private String clinicName;
    private Date date;

    public AppointmentDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.specializationName = appointment.getSpecialization().getName();
        this.clinicName = appointment.getClinic().getNamextraInfose();
        this.date = appointment.getDate();
    }

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
