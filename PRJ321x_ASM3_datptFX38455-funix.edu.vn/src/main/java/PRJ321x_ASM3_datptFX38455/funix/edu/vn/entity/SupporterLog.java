package PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "supporterlogs")
public class SupporterLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "supporterId")
    private User supporter;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "created_at")
    private java.util.Date createdAt;

    @Column(name = "updated_at")
    private java.util.Date updatedAt;

    @Column(name = "deleted_at")
    private java.util.Date deletedAt;

    // Getters and Setters
}
