package cn.grassit.grassitbackend.AppInfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppInfo {

    @Value("${application.version}")
    public String version;

}
