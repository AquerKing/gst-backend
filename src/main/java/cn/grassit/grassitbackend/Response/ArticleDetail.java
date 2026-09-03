package cn.grassit.grassitbackend.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleDetail {
    @JsonProperty("uuid")
    public String uuid;

    @JsonProperty("slug")
    public String slug;

    @JsonProperty("authorId")
    public BigInteger authorId;

    @JsonProperty("authorName")
    public String authorName;

    @JsonProperty("createdAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime updatedAt;

    @JsonProperty("content")
    public String content;

    @JsonProperty("toc")
    public List<Object> toc;

    @JsonProperty("attachments")
    public List<Attachment> attachments;
}
