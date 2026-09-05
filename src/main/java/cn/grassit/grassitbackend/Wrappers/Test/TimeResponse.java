package cn.grassit.grassitbackend.Wrappers.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public class TimeResponse {

    @JsonProperty("time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime dateTime;

    @JsonProperty("version")
    @Value("${application.version}")
    public String version;

}
