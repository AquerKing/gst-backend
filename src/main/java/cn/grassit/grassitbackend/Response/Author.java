package cn.grassit.grassitbackend.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class Author {

    @JsonProperty("username")
    public String username;

    @JsonProperty("nickname")
    public String nickname;

}
