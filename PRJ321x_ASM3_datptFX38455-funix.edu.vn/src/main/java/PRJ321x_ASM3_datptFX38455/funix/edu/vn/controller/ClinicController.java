package PRJ321x_ASM3_datptFX38455.funix.edu.vn.controller;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.ClinicsDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.SpecializationDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.entity.Clinic;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping("/clinic/details")
    public ResponseEntity<Map<String, Object>> getClinicById(@RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            ClinicsDTO clinic = clinicService.getClinicById(id);
            if (clinic != null) {
                response.put("status", "success");
                response.put("data", clinic);
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "Clinic not found");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/clinics")
    public ResponseEntity<Map<String, Object>> getTopSpecializations() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ClinicsDTO> clinicsDTOS = clinicService.getAllClinics();
            response.put("status", "success");
            response.put("data", clinicsDTOS);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred");
            return ResponseEntity.status(500).body(response);
        }
    }





@GetMapping("/clinics/searchByName")
public ResponseEntity<Map<String, Object>> searchClinicsByName(
        @RequestParam String name) {

    Map<String, Object> response = new HashMap<>();

    try {
        List<ClinicsDTO> clinics = clinicService.searchByName(name);

        response.put("status", "success");
        response.put("message", "Data fetched successfully.");
        response.put("data", clinics);

        return ResponseEntity.ok(response);

    } catch (Exception e) {
        response.put("status", "error");
        response.put("message", "An error occurred: " + e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

    @GetMapping("/clinics/searchByAddress")
    public ResponseEntity<Map<String, Object>> searchClinicsByAddress(
            @RequestParam String address) {

        Map<String, Object> response = new HashMap<>();

        try {
            List<ClinicsDTO> clinics = clinicService.searchByAddress(address);

            response.put("status", "success");
            response.put("message", "Data fetched successfully.");
            response.put("data", clinics);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/clinics/searchByPhone")
    public ResponseEntity<Map<String, Object>> searchClinicsByPhone(
            @RequestParam String phone) {

        Map<String, Object> response = new HashMap<>();

        try {
            List<ClinicsDTO> clinics = clinicService.searchByPhone(phone);

            response.put("status", "success");
            response.put("message", "Data fetched successfully.");
            response.put("data", clinics);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/clinics/searchByCost")
    public ResponseEntity<Map<String, Object>> searchByCost(
            @RequestParam String cost) {

        Map<String, Object> response = new HashMap<>();

        try {
            List<ClinicsDTO> clinics = clinicService.searchByCost(cost);

            response.put("status", "success");
            response.put("message", "Data fetched successfully.");
            response.put("data", clinics);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/clinics/search")
    public ResponseEntity<Map<String, Object>> searchClinics(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String cost) {

        Map<String, Object> response = new HashMap<>();

        try {
            List<ClinicsDTO> clinics = clinicService.searchClinics(name, address, phone, cost);

            response.put("status", "success");
            response.put("message", "Data fetched successfully.");
            response.put("data", clinics);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}