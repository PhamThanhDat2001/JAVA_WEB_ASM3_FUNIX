package PRJ321x_ASM3_datptFX38455.funix.edu.vn.controller;

import PRJ321x_ASM3_datptFX38455.funix.edu.vn.config.JwtTokenUtil;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.dto.SpecializationDTO;
import PRJ321x_ASM3_datptFX38455.funix.edu.vn.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/specializations")
    public ResponseEntity<Map<String, Object>> getTopSpecializations() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<SpecializationDTO> specializations = specializationService.getAllSpecializations();
            response.put("status", "success");
            response.put("data", specializations);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred");
            return ResponseEntity.status(500).body(response);
        }
    }
    @GetMapping("/specialization/top")
    public ResponseEntity<Map<String, Object>> getTopSpecialization() {
        Map<String, Object> response = new HashMap<>();
        try {
            SpecializationDTO topSpecialization = specializationService.getTopSpecialization();
            response.put("status", "success");
            response.put("data", topSpecialization);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred");
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/specializations/searchByName")
    public ResponseEntity<Map<String, Object>> searchByName(
            @RequestParam String name) {

        Map<String, Object> response = new HashMap<>();

        try {
            List<SpecializationDTO> specializations = specializationService.searchByName(name);

            response.put("status", "success");
            response.put("message", "Data fetched successfully.");
            response.put("data", specializations);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/specialization/name/{specializationId}")
    public ResponseEntity<?> getSpecializationNameById(@PathVariable Integer specializationId) {
        return specializationService.getSpecializationNameById(specializationId)
                .map(specializationDTO -> ResponseEntity.ok().body(specializationDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
