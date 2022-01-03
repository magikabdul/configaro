package cloud.cholewa.health.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/health")
@CrossOrigin("*")
public class HealthController {

    @GetMapping
    public ResponseEntity<String> reportHealth() {
        return ResponseEntity.ok("It's working, and now is: " + new Date());
    }
}
