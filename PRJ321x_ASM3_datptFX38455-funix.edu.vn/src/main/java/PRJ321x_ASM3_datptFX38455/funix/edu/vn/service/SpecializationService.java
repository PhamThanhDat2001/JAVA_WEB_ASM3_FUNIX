package PRJ321x_ASM3_datptFX38455.funix.edu.vn.service;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.SpecializationDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Specialization;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository.SpecializationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

//    public SpecializationDTO getTopSpecialization() {
//        Specialization topSpecialization = specializationRepository.findTopSpecializations();
//        // Chuyển đổi entity thành DTO
//        return new SpecializationDTO(topSpecialization.getId(), topSpecialization.getName(), topSpecialization.getDescription(), topSpecialization.getImage());
//    }

    public SpecializationDTO getTopSpecialization() {
        Specialization topSpecialization = specializationRepository.findTopSpecializations();

        if (topSpecialization == null) {
            return null; // Hoặc xử lý trường hợp không tìm thấy chuyên môn
        }

        // Chuyển đổi entity thành DTO
        return new SpecializationDTO(
                topSpecialization.getId(),          // ID
                topSpecialization.getDescription(),        // Tên chuyên môn
                topSpecialization.getName(), // Mô tả chuyên môn
                topSpecialization.getImage()        // Hình ảnh chuyên môn
        );
    }

    //    public List<Specialization> getAllSpecializations() {
//        return specializationRepository.findAll();
//    }
public List<SpecializationDTO> searchByName(String name) {
    List<Specialization> specializations = specializationRepository.findByNameContainingIgnoreCase(name);
    return specializations.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
}
    private SpecializationDTO convertToDTO(Specialization specialization) {
        return new SpecializationDTO(
                specialization.getId(),
                specialization.getDescription(),
                specialization.getName(),
                specialization.getImage()
        );
    }
    public List<SpecializationDTO> getAllSpecializations() {
        List<Specialization> specializations = specializationRepository.findAll();
        return specializations.stream()
                .map(specialization -> {
                    SpecializationDTO dto = new SpecializationDTO();
                    dto.setId(specialization.getId());
                    dto.setName(specialization.getName());
                    dto.setDescription(specialization.getDescription());
                    dto.setImage(specialization.getImage());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Optional<SpecializationDTO> getSpecializationNameById(Integer id) {
        return specializationRepository.findById(id)
                .map(specialization -> new SpecializationDTO(specialization.getName()));
    }
}
