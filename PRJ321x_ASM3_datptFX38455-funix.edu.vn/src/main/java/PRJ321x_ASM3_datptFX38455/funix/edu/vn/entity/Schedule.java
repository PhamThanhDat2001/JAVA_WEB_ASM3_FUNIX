package PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private DoctorUser doctor;

    @Column(name = "date", length = 255)
    private String date;

    @Column(name = "time", length = 255)
    private String time;

    @Column(name = "maxBooking", length = 255)
    private String maxBooking;

    @Column(name = "sumBooking", length = 255)
    private String sumBooking;

    @Column(name = "created_at")
    private java.util.Date createdAt;

    @Column(name = "updated_at")
    private java.util.Date updatedAt;

    @Column(name = "deleted_at")
    private java.util.Date deletedAt;

    // Getters and Setters
}
