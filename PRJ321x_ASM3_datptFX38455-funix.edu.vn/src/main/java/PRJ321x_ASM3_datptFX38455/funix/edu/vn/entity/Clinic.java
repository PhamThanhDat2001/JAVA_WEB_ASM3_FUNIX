package PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "clinics")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "namextra_infose", length = 255)
    private String namextraInfose;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "phone", length = 255)
    private String phone;

    @Lob
    @Column(name = "introduction_html")
    private String introductionHTML;

    @Lob
    @Column(name = "introduction_markdown")
    private String introductionMarkdown;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

//    @OneToMany(mappedBy = "clinic")
//    private List<Post> posts;
    @ManyToMany
    @JoinTable(
            name = "clinic_specializations",
            joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private List<Specialization> specializations;
    // Getters and Setters
    @Column(name = "working_hours")
    private String workingHours;
    @Column(name = "important_notes")
    private String importantNotes;
    @Column(name = "consultation_cost")
    private BigDecimal consultationCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamextraInfose() {
        return namextraInfose;
    }

    public void setNamextraInfose(String namextraInfose) {
        this.namextraInfose = namextraInfose;
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

//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
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
}
