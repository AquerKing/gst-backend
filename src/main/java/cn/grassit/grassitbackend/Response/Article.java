package cn.grassit.grassitbackend.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @JsonProperty("uuid")
    public String uuid;

    @JsonProperty("slug")
    public String slug;

    @JsonProperty("author")
    public Author author;

    @JsonProperty("createdAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime updatedAt;

    @JsonProperty("content")
    public String content;

    @JsonProperty("tags")
    public List<String> tags;

    @JsonProperty("attachments")
    public List<Attachment> attachments;

}
