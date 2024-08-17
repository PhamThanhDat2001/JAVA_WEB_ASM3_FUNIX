package PRJ321x_ASM3_datptFX38455.funix.edu.vn.service;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Appointment;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    public List<Appointment> getAppointmentsByUser(Long userId) {
        return appointmentRepository.findByUserId(userId);
    }
}
