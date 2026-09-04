package cn.grassit.grassitbackend.Database.Rows;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleBaseInfo {
    public String uuid;
    public String slug;
    public BigInteger authorId;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
