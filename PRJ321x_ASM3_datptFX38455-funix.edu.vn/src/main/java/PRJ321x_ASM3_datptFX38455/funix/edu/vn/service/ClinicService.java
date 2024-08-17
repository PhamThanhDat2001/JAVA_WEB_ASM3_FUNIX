package PRJ321x_ASM3_datptFX38455.funix.edu.vn.service;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.ClinicsDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Clinic;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;


    public ClinicsDTO getClinicById(Long id) {
        Optional<Clinic> clinic = clinicRepository.findById(id);
        return clinic.map(this::convertToDTO).orElse(null);
    }

    public List<ClinicsDTO> getAllClinics() {
        List<Clinic> clinics = clinicRepository.findAll();
        return clinics.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public List<ClinicsDTO> searchByName(String name) {
        List<Clinic> clinics = clinicRepository.findByNamextraInfoseContaining(name);
        return clinics.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ClinicsDTO> searchByAddress(String address) {
        List<Clinic> clinics = clinicRepository.findByAddressContaining(address);
        return clinics.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ClinicsDTO> searchByPhone(String phone) {
        List<Clinic> clinics = clinicRepository.findByPhoneContaining(phone);
        return clinics.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<ClinicsDTO> searchByCost(String costRange) {
        // Chia khoảng giá thành các phần tử min và max
        String[] parts = costRange.split("-");
        BigDecimal minCost = new BigDecimal(parts[0].trim());
        BigDecimal maxCost = new BigDecimal(parts[1].trim());

        List<Clinic> clinics = clinicRepository.findByConsultationCostBetween(minCost, maxCost);
        return clinics.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Chuyển đổi từ entity Clinic sang DTO ClinicsDTO
    private ClinicsDTO convertToDTO(Clinic clinic) {
        return new ClinicsDTO(
                clinic.getId(),
                clinic.getNamextraInfose(),
                clinic.getAddress(),
                clinic.getPhone(),
                clinic.getIntroductionHTML(),
                clinic.getIntroductionMarkdown(),
                clinic.getDescription(),
                clinic.getImage(),
                clinic.getWorkingHours(),
                clinic.getImportantNotes(),
                clinic.getConsultationCost(),
                clinic.getCreatedAt(),
                clinic.getUpdatedAt(),
                clinic.getDeletedAt()
        );
    }


//    public List<Clinic> searchByName(String name) {
//        return clinicRepository.findByNamextraInfoseContaining(name);
//    }
//
//    public List<Clinic> searchByAddress(String address) {
//        return clinicRepository.findByAddressContaining(address);
//    }
//
//    public List<Clinic> searchByPhone(String phone) {
//        return clinicRepository.findByPhone(phone);
//    }

    public List<ClinicsDTO> searchClinics(String name, String address, String phone, String cost) {
        // Khởi tạo danh sách kết quả
        List<Clinic> clinics = clinicRepository.findAll(); // Giả sử bạn có một phương thức findAll trong ClinicRepository

        // Lọc kết quả theo các tiêu chí
        if (name != null && !name.isEmpty()) {
            clinics = clinics.stream()
                    .filter(clinic -> clinic.getNamextraInfose().contains(name))
                    .collect(Collectors.toList());
        }

        if (address != null && !address.isEmpty()) {
            clinics = clinics.stream()
                    .filter(clinic -> clinic.getAddress().contains(address))
                    .collect(Collectors.toList());
        }

        if (phone != null && !phone.isEmpty()) {
            clinics = clinics.stream()
                    .filter(clinic -> clinic.getPhone().contains(phone))
                    .collect(Collectors.toList());
        }

        if (cost != null && !cost.isEmpty()) {
            // Chuyển đổi khoảng giá thành số và lọc kết quả
            String[] costRange = cost.split("-");
            if (costRange.length == 2) {
                try {
                    BigDecimal minCost = new BigDecimal(costRange[0].trim());
                    BigDecimal maxCost = new BigDecimal(costRange[1].trim());

                    clinics = clinics.stream()
                            .filter(clinic -> clinic.getConsultationCost().compareTo(minCost) >= 0 &&
                                    clinic.getConsultationCost().compareTo(maxCost) <= 0)
                            .collect(Collectors.toList());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid cost range format.");
                }
            }
        }

        return clinics.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


}
