package PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class SpecializationDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private BigDecimal consultationCost;



    private Date createdAt;
    private Date updatedAt;
    private List<DoctorDTO> doctorUsers;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<DoctorDTO> getDoctorUsers() {
        return doctorUsers;
    }

    public void setDoctorUsers(List<DoctorDTO> doctorUsers) {
        this.doctorUsers = doctorUsers;
    }

    public SpecializationDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public SpecializationDTO() {
    }

    public SpecializationDTO(Integer id, String description, String name, String image) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.image = image;
    }

    public SpecializationDTO(Integer id, String description, String image, String name, BigDecimal consultationCost) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.name = name;
        this.consultationCost = consultationCost;
    }
    public SpecializationDTO(Integer id, String name, String description, BigDecimal consultationCost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.consultationCost = consultationCost;
    }

    public SpecializationDTO(String name) {
        this.name = name;
    }
    // Getters và Setters


    public BigDecimal getConsultationCost() {
        return consultationCost;
    }

    public void setConsultationCost(BigDecimal consultationCost) {
        this.consultationCost = consultationCost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
