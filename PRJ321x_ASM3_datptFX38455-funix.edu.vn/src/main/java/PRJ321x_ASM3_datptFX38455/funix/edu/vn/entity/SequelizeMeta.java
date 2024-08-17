package PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sequelizemeta")
public class SequelizeMeta {
    @Id
    @Column(name = "name", length = 255)
    private String name;

    // Getters and Setters
}
