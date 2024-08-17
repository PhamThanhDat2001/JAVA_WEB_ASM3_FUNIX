package PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "specializations")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "consultation_cost")
    private BigDecimal consultationCost;

    @Column(name = "created_at")
    private java.util.Date createdAt;

    @Column(name = "updated_at")
    private java.util.Date updatedAt;

    @Column(name = "deleted_at")
    private java.util.Date deletedAt;
//    @OneToMany(mappedBy = "specialization")
//    private List<Post> posts;

//    @OneToMany(mappedBy = "specialization")
//    private List<Appointment> appointments;

    @OneToMany(mappedBy = "specialization")
    private List<DoctorUser> doctorUsers;


    @ManyToMany(mappedBy = "specializations")
    private List<Clinic> clinics;


    // Getters and Setters

    public BigDecimal getConsultationCost() {
        return consultationCost;
    }

    public void setConsultationCost(BigDecimal consultationCost) {
        this.consultationCost = consultationCost;
    }

    public List<DoctorUser> getDoctorUsers() {
        return doctorUsers;
    }

    public void setDoctorUsers(List<DoctorUser> doctorUsers) {
        this.doctorUsers = doctorUsers;
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }

//    public List<Appointment> getAppointments() {
//        return appointments;
//    }
//
//    public void setAppointments(List<Appointment> appointments) {
//        this.appointments = appointments;
//    }
}
