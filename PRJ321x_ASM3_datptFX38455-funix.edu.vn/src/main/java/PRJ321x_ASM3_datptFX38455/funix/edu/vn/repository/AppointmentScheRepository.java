package PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.AppointmentDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.AppointmentScheDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentScheRepository extends JpaRepository<AppointmentSche, Integer> {
    List<AppointmentSche> findByDoctorId(Integer doctorId);




    @Query("SELECT new PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.AppointmentScheDTO(" +
            "a.id, a.specializationId, a.clinicId, a.doctorId, a.userId, a.date, a.consultationCost, a.name, " +
            "a.dateTime, a.gender, a.phone, a.address, a.note, a.statusId, a.reason, " +
            "s.name, s.description, c.namextraInfose, c.address, c.phone, d.name, d.email, u.name, u.email) " +
            "FROM AppointmentSche a " +
            "JOIN Specialization s ON a.specializationId = s.id " +
            "JOIN Clinic c ON a.clinicId = c.id " +
            "JOIN User d ON a.doctorId = d.id " +
            "JOIN User u ON a.userId = u.id " +
            "WHERE a.userId = :userId")
    List<AppointmentScheDTO> findAppointmentsByUserId(@Param("userId") Integer userId);

    @Query("SELECT new PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.AppointmentScheDTO(" +
            "a.id, a.specializationId, a.clinicId, a.doctorId, a.userId, a.date, a.consultationCost, a.name, " +
            "a.dateTime, a.gender, a.phone, a.address, a.note, a.statusId, a.reason, " +
            "s.name, s.description, c.namextraInfose, c.address, c.phone, d.name, d.email, u.name, u.email) " +
            "FROM AppointmentSche a " +
            "JOIN Specialization s ON a.specializationId = s.id " +
            "JOIN Clinic c ON a.clinicId = c.id " +
            "JOIN User d ON a.doctorId = d.id " +
            "JOIN User u ON a.userId = u.id " +
            "WHERE a.doctorId = :doctorId")
    List<AppointmentScheDTO> findAppointmentsByDoctorId(@Param("doctorId") Integer doctorId);


}