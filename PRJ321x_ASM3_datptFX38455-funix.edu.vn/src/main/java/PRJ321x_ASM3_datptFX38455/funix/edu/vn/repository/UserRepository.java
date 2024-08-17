package PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
//    User findByResetToken(String token);
//    void saveResetToken(String email, String token);
User findByResetToken(String resetToken);
    // Phương thức trả về Optional<User>
    Optional<User> findOptionalByEmail(String email);
    @Modifying
    @Query("UPDATE User u SET u.resetToken = :token WHERE u.email = :email")
    void updateResetToken(@Param("email") String email, @Param("token") String token);

    User findUserByEmail(String email);
    // Tìm người dùng theo role_id
    List<User> findByRoleId(Long roleId);
    boolean existsByEmail(String email);
}
