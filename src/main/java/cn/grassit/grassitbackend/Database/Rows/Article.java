package cn.grassit.grassitbackend.Database.Rows;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class Article {
    private String uuid;
    private String slug;
    private BigInteger author_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
