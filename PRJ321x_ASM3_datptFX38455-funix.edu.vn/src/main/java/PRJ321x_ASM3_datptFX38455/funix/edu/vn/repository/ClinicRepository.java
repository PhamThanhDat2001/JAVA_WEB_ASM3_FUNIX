package PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    // Tìm kiếm theo tên
    List<Clinic> findByNamextraInfoseContaining(String name);

    // Tìm kiếm theo địa chỉ
    List<Clinic> findByAddressContaining(String address);

    // Tìm kiếm theo số điện thoại
    List<Clinic> findByPhoneContaining(String phone);

    // Tìm kiếm các phòng khám có giá khám trong một khoảng giá
    @Query("SELECT c FROM Clinic c WHERE c.consultationCost BETWEEN :minCost AND :maxCost")
    List<Clinic> findByConsultationCostBetween(@Param("minCost") BigDecimal minCost, @Param("maxCost") BigDecimal maxCost);

//    List<Clinic> findAll();

}
