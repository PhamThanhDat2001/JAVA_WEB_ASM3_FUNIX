package PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ClinicsDTO {

    private Long id;
    private String nameExtraInfo;
    private String address;
    private String phone;
    private String introductionHTML;
    private String introductionMarkdown;
    private String description;
    private String image;
    private String workingHours;
    private String importantNotes;
    private BigDecimal consultationCost;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    // Constructors
    public ClinicsDTO() {
    }

    public ClinicsDTO(Long id, String nameExtraInfo, String address, String phone, String introductionHTML, 
                      String introductionMarkdown, String description, String image, String workingHours, 
                      String importantNotes, BigDecimal consultationCost, LocalDateTime createdAt, 
                      LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.nameExtraInfo = nameExtraInfo;
        this.address = address;
        this.phone = phone;
        this.introductionHTML = introductionHTML;
        this.introductionMarkdown = introductionMarkdown;
        this.description = description;
        this.image = image;
        this.workingHours = workingHours;
        this.importantNotes = importantNotes;
        this.consultationCost = consultationCost;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameExtraInfo() {
        return nameExtraInfo;
    }

    public void setNameExtraInfo(String nameExtraInfo) {
        this.nameExtraInfo = nameExtraInfo;
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

    public String getIntroductionHTML() {
        return introductionHTML;
    }

    public void setIntroductionHTML(String introductionHTML) {
        this.introductionHTML = introductionHTML;
    }

    public String getIntroductionMarkdown() {
        return introductionMarkdown;
    }

    public void setIntroductionMarkdown(String introductionMarkdown) {
        this.introductionMarkdown = introductionMarkdown;
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

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getImportantNotes() {
        return importantNotes;
    }

    public void setImportantNotes(String importantNotes) {
        this.importantNotes = importantNotes;
    }

    public BigDecimal getConsultationCost() {
        return consultationCost;
    }

    public void setConsultationCost(BigDecimal consultationCost) {
        this.consultationCost = consultationCost;
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
}
