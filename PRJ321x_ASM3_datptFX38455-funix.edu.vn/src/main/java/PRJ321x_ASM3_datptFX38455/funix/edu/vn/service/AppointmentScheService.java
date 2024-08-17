package PRJ321x_ASM3_datptFX38455.funix.edu.vn.service;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.AppointmentDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.AppointmentScheDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.DoctorDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.SpecializationDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.*;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentScheService {

    @Autowired
    private AppointmentScheRepository appointmentScheRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusRepository statusRepository;

//    public List<AppointmentScheDTO> getAppointmentsByDoctorId(Integer doctorId) {
//        return appointmentScheRepository.findByDoctorId(doctorId);
//    }

    public AppointmentSche createAppointment(AppointmentScheDTO appointmentScheDTO) {
        AppointmentSche appointmentSche = new AppointmentSche();


        appointmentSche.setSpecializationId(appointmentScheDTO.getSpecializationId());
        appointmentSche.setClinicId(appointmentScheDTO.getClinicId());
        appointmentSche.setDoctorId(appointmentScheDTO.getDoctorId());
        appointmentSche.setUserId(appointmentScheDTO.getUserId());

        appointmentSche.setDate(appointmentScheDTO.getDate());
        appointmentSche.setConsultationCost(appointmentScheDTO.getConsultationCost());
        appointmentSche.setName(appointmentScheDTO.getName());
        appointmentSche.setDateTime(appointmentScheDTO.getDateTime());
        appointmentSche.setGender(appointmentScheDTO.getGender());
        appointmentSche.setPhone(appointmentScheDTO.getPhone());
        appointmentSche.setAddress(appointmentScheDTO.getAddress());
        appointmentSche.setNote(appointmentScheDTO.getNote());
//  Status mặc định từ cơ sở dữ liệu

        appointmentSche.setStatusId(1);
        return appointmentScheRepository.save(appointmentSche);
    }
    public List<AppointmentScheDTO> getAppointmentsByDoctorId(Integer doctorId) {
        return appointmentScheRepository.findByDoctorId(doctorId).stream()
                .map(appointment -> new AppointmentScheDTO(
                        appointment.getId(),
                        appointment.getSpecializationId(),
                        appointment.getClinicId(),
                        appointment.getDoctorId(),
                        appointment.getUserId(),
                        appointment.getDate(),
                        appointment.getConsultationCost(),
                        appointment.getName(),
                        appointment.getDateTime(),
                        appointment.getGender(),
                        appointment.getPhone(),
                        appointment.getAddress(),
                        appointment.getNote(),
                        appointment.getStatusId(),
                        appointment.getReason()
                ))
                .collect(Collectors.toList());
    }

    public AppointmentSche updateAppointmentStatus(Integer appointmentId, int status) {
        AppointmentSche appointment = appointmentScheRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        appointment.setStatusId(status);
        return appointmentScheRepository.save(appointment);
    }

    public void updateAppointmentStatusCancel(Integer appointmentId, Integer status, String reason) {
        AppointmentSche appointment = appointmentScheRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        // Cập nhật trạng thái và lý do
        appointment.setStatusId(status);
        appointment.setReason(reason);

        // Lưu đối tượng cập nhật vào cơ sở dữ liệu
        appointmentScheRepository.save(appointment);
    }


//public List<AppointmentSche> getAppointmentsByUserId(Integer userId) {
//    return appointmentScheRepository.findByUserId(userId);
//}



    public List<AppointmentScheDTO> getAppointmentsByUserId(Integer userId) {
        return appointmentScheRepository.findAppointmentsByUserId(userId);
    }
    public List<AppointmentScheDTO> getAppointmentsByDtId(Integer doctorId) {
        return appointmentScheRepository.findAppointmentsByDoctorId(doctorId);
    }

}
