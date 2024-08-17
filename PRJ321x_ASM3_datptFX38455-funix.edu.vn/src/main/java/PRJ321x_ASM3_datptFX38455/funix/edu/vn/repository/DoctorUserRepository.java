package PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.DoctorUser;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorUserRepository extends JpaRepository<DoctorUser, Integer> {
//    List<DoctorUser> findBySpecializationId(Integer specializationId);
//    @Query("SELECT du.doctor FROM DoctorUser du WHERE du.specialization.id = :specializationId")
//    List<User> findDoctorsBySpecializationId(@Param("specializationId") Integer specializationId);
@Query("SELECT du FROM DoctorUser du WHERE du.specialization.id = :specializationId")
List<DoctorUser> findDoctorsBySpecializationId(Integer specializationId);
    List<DoctorUser> findAll();
}
