package cn.grassit.grassitbackend.Database.Api;

import cn.grassit.grassitbackend.Database.Predefinitions.Tables;
import cn.grassit.grassitbackend.Database.Rows.Article;
import cn.grassit.grassitbackend.Response.ArticleDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DBTools {
    private final JdbcTemplate jdbcTemplate;

    public ArticleDetail getArticleByUuid(String uuid) {
        String sql = "SELECT * FROM %s WHERE uuid = '%s'".formatted(Tables.Articles, uuid);
        List<Article> list = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Article.class)
        );

        assert list.size() == 1;
        Article article = list.getFirst();

        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.uuid = article.getUuid();
        articleDetail.slug = article.getSlug();
        articleDetail.authorId = article.getAuthor_id();
        articleDetail.createdAt = article.getCreated_at();
        articleDetail.updatedAt = article.getUpdated_at();

        return articleDetail;
    }
}
