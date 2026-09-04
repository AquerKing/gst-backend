package cn.grassit.grassitbackend.Wrappers.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class DateTime {
    @JsonProperty("time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime dateTime;
}
