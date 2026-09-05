package cn.grassit.grassitbackend.Controllers.Api.Test;

import cn.grassit.grassitbackend.AppInfo.AppInfo;
import cn.grassit.grassitbackend.Wrappers.Test.TimeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class TimeController {

    private final AppInfo appInfo;

    @GetMapping({"/api/test", "/api"})
    public ResponseEntity<TimeResponse> now() {
        TimeResponse timeResponse = new TimeResponse();
        timeResponse.dateTime = LocalDateTime.now();
        timeResponse.version = appInfo.version;
        return ResponseEntity.ok(timeResponse);
    }
}
