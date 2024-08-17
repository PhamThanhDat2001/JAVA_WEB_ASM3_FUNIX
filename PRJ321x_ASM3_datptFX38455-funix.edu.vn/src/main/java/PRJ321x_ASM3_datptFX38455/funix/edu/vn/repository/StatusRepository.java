package PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
}
