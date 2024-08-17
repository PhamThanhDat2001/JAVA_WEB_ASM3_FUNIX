package PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private DoctorUser doctor;

    @Column(name = "timeBooking", length = 255)
    private String timeBooking;

    @Column(name = "dateBooking", length = 255)
    private String dateBooking;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "phone", length = 255)
    private String phone;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private Boolean status; // TINYINT(1) can be mapped to Boolean

    @Column(name = "created_at")
    private java.util.Date createdAt;

    @Column(name = "updated_at")
    private java.util.Date updatedAt;

    @Column(name = "deleted_at")
    private java.util.Date deletedAt;

    // Getters and Setters
}
