package PRJ321x_ASM3_datptFX38455.funix.edu.vn.controller;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.AppointmentDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.AppointmentScheDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.AppointmentSche;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.service.AppointmentScheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AppointmentScheController {

    @Autowired
    private AppointmentScheService appointmentScheService;



    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentScheDTO>> getAppointmentsByDoctorId(@PathVariable Integer doctorId) {
        List<AppointmentScheDTO> appointments = appointmentScheService.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/doctor/{appointmentId}/accept")
    public ResponseEntity<String> acceptAppointment(@PathVariable Integer appointmentId) {
        appointmentScheService.updateAppointmentStatus(appointmentId, 2);  // Cập nhật trạng thái thành 2 (Đã xác nhận)
        return ResponseEntity.ok("Appointment accepted.");
    }

//    @PutMapping("/doctor/{appointmentId}/cancel")
//    public ResponseEntity<String> cancelAppointment(@PathVariable Integer appointmentId) {
//        appointmentScheService.updateAppointmentStatus(appointmentId, 3);  // Cập nhật trạng thái thành 3 (Hủy bỏ)
//        return ResponseEntity.ok("Appointment canceled.");
//    }
@PutMapping("/doctor/{appointmentId}/cancel")
public ResponseEntity<String> cancelAppointment(
        @PathVariable Integer appointmentId,
        @RequestBody AppointmentScheDTO appointmentRequest) {

    // Update appointment status to "Canceled" (3) and set the reason
    appointmentScheService.updateAppointmentStatusCancel(appointmentId, 3, appointmentRequest.getReason());

    return ResponseEntity.ok("Appointment canceled.");
}
    @PostMapping("/schedule/appointmentss")
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentScheDTO appointmentScheDTO) {
        AppointmentSche createdAppointment = appointmentScheService.createAppointment(appointmentScheDTO);

        return ResponseEntity.ok("Appointment created successfully with ID: " + createdAppointment.getId());
    }

    @GetMapping("/appointments/user")
    public List<AppointmentScheDTO> getAppointmentsByUserId(@RequestParam Integer userId) {
        return appointmentScheService.getAppointmentsByUserId(userId);
    }
  @GetMapping("/appointments/doctordt")
    public List<AppointmentScheDTO> getAppointmentsByUdoctorId(@RequestParam Integer doctorId) {
        return appointmentScheService.getAppointmentsByDtId(doctorId);
    }

}
