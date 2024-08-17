package PRJ321x_ASM3_datptFX38455.funix.edu.vn.controller;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.DoctorUserDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.SpecializationDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.UserDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.DoctorUser;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.User;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.service.DoctorUserService;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {

    @Autowired
    private DoctorUserService doctorUserService;

    @GetMapping("/schedule/specialization")
    public ResponseEntity<List<UserDTO>> getDoctorsBySpecialization(@RequestParam Integer specId) {
        List<UserDTO> userDTOs = doctorUserService.getDoctorsBySpecializationId(specId);
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<UserDTO>> getAllDoctors() {
        List<UserDTO> userDTOs = doctorUserService.getAllDoctors();
        return ResponseEntity.ok(userDTOs);
    }

    @PostMapping("/doctor/register")
    public ResponseEntity<?> createDoctor(@RequestBody DoctorUserDTO doctorUserDTO) {
        try {
            // Thêm mới User
            UserDTO userDTO = doctorUserDTO.getDoctor(); // Lấy thông tin UserDTO từ DoctorUserDTO
            User newUser = doctorUserService.createUser(userDTO);

            // Cập nhật doctorId trong DoctorUserDTO
            doctorUserDTO.setDoctorId(newUser.getId().intValue());

            // Thêm mới DoctorUser với các thông tin bổ sung
            DoctorUserDTO newDoctorUserDTO = new DoctorUserDTO();
            newDoctorUserDTO.setDoctorId(doctorUserDTO.getDoctorId());
            newDoctorUserDTO.setClinicId(doctorUserDTO.getClinicId());
            newDoctorUserDTO.setSpecializationId(doctorUserDTO.getSpecializationId());
            newDoctorUserDTO.setCreatedAt(doctorUserDTO.getCreatedAt());
            newDoctorUserDTO.setUpdatedAt(doctorUserDTO.getUpdatedAt());
            newDoctorUserDTO.setDeletedAt(doctorUserDTO.getDeletedAt());

            // Thực hiện lưu DoctorUser
            DoctorUser savedDoctorUserDTO = doctorUserService.createDoctorUser(newDoctorUserDTO);

            return ResponseEntity.ok(savedDoctorUserDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }





}
