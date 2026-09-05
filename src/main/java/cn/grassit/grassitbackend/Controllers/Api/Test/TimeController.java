package cn.grassit.grassitbackend.Controllers.Api.Test;

import cn.grassit.grassitbackend.Wrappers.Test.DateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TimeController {

    @GetMapping({"/api/test", "/api"})
    public ResponseEntity<DateTime> now() {
        DateTime dateTime = new DateTime();
        dateTime.dateTime = LocalDateTime.now();
        return ResponseEntity.ok(dateTime);
    }
}
