package cn.grassit.grassitbackend.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class Attachment {
    @JsonProperty("url")
    public String url;

    @JsonProperty("originalName")
    public String originalName;

    @JsonProperty("size")
    public BigInteger fileSize;
}
