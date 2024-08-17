package PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository;


import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {

    @Query("SELECT s FROM Specialization s ORDER BY (SELECT COUNT(a.id) FROM Appointment a WHERE a.specialization.id = s.id) DESC Limit 1")
    Specialization findTopSpecializations();

    List<Specialization> findByNameContainingIgnoreCase(String name);

}
