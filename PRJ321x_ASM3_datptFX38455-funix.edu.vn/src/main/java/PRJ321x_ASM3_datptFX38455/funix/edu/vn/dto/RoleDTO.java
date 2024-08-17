package PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class RoleDTO {
    private Integer id;
    private String name;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    // Constructor, Getters và Setters

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
}

