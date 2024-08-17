package PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "extrainfos")
public class ExtraInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(name = "historyBreath")
    private String historyBreath;

    @Column(name = "oldForms")
    private String oldForms;

    @Column(name = "sendForms")
    private String sendForms;

    @Column(name = "moreInfo")
    private String moreInfo;

    @Column(name = "created_at")
    private java.util.Date createdAt;

    @Column(name = "updated_at")
    private java.util.Date updatedAt;

    @Column(name = "deleted_at")
    private java.util.Date deletedAt;

    // Getters and Setters
}
