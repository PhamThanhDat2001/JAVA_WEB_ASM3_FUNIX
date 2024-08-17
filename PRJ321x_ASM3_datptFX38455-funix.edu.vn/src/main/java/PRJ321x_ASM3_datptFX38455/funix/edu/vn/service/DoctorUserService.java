package PRJ321x_ASM3_datptFX38455.funix.edu.vn.service;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.config.ResourceNotFoundException;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.DoctorUserDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.SpecializationDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.UserDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.*;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorUserService {

    @Autowired
    private DoctorUserRepository doctorUserRepository;



    public List<UserDTO> getDoctorsBySpecializationId(Integer specializationId) {
        List<DoctorUser> doctorUsers = doctorUserRepository.findDoctorsBySpecializationId(specializationId);
        return doctorUsers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<UserDTO> getAllDoctors() {
        List<DoctorUser> doctorUsers = doctorUserRepository.findAll();
        return doctorUsers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private UserDTO convertToDTO(DoctorUser doctorUser) {
        User user = doctorUser.getDoctor();
        SpecializationDTO specializationDTO = null;
        if (doctorUser.getSpecialization() != null) {
            specializationDTO = new SpecializationDTO(
                    doctorUser.getSpecialization().getId(),
                    doctorUser.getSpecialization().getName(),
                    doctorUser.getSpecialization().getDescription(),
                    doctorUser.getSpecialization().getConsultationCost()
            );
        }

        // Lấy giá trị namextraInfose và clinicId từ Clinic liên quan đến DoctorUser
        String namextraInfose = null;
        Long clinicId = null;
        if (doctorUser.getClinic() != null) {
            namextraInfose = doctorUser.getClinic().getNamextraInfose();
            clinicId = doctorUser.getClinic().getId();
        }

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAddress(),
                user.getPhone(),
                user.getAvatar(),
                user.getDescription(),
                specializationDTO,
                namextraInfose, // Gán giá trị namextraInfose vào UserDTO
                clinicId, // Gán giá trị clinicId vào UserDTO
                user.getTrainingProcess(),
                user.getAchievement(),
                user.getReason(),
                user.getActive()
        );
    }
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClinicRepository clinicRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SpecializationRepository specializationRepository;
    public User createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email đã tồn tại.");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setGender(userDTO.getGender());
        user.setAvatar(userDTO.getAvatar());
        user.setDescription(userDTO.getDescription());
        user.setTrainingProcess(userDTO.getTrainingProcess());
        user.setAchievement(userDTO.getAchievement());
        user.setReason(userDTO.getReason());
        user.setActive(true);

        // Đặt mật khẩu mặc định và mã hóa nó
        String defaultPassword = "$2a$12$DXEH11r1ll0lL5Wnd/efOuoWp9O3NQhSUOLMpI9n4xo2WcPdp0Dz6";
        user.setPassword(defaultPassword);

        // Cài đặt role mặc định nếu chưa có
        Role defaultRole = roleRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Role with id 2 not found"));
        user.setRole(defaultRole);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }


    public DoctorUser createDoctorUser(DoctorUserDTO doctorUserDTO) {
        DoctorUser doctorUser = new DoctorUser();
        doctorUser.setDoctorId(doctorUserDTO.getDoctorId());
        doctorUser.setClinicId(doctorUserDTO.getClinicId());
        doctorUser.setSpecializationId(doctorUserDTO.getSpecializationId());
        doctorUser.setCreatedAt(doctorUserDTO.getCreatedAt());
        doctorUser.setUpdatedAt(doctorUserDTO.getUpdatedAt());
        doctorUser.setDeletedAt(doctorUserDTO.getDeletedAt());

        // Lưu đối tượng DoctorUser vào cơ sở dữ liệu
        return doctorUserRepository.save(doctorUser);
    }




}

