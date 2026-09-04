package cn.grassit.grassitbackend.Database.Api;

import cn.grassit.grassitbackend.Database.Predefinitions.Tables;
import cn.grassit.grassitbackend.Database.Predefinitions.UserBaseTableFields;
import cn.grassit.grassitbackend.Database.Rows.ArticleBaseInfo;
import cn.grassit.grassitbackend.Database.Rows.UserBaseInfo;
import cn.grassit.grassitbackend.Response.Article;
import cn.grassit.grassitbackend.Response.Author;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DBTools {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Article> getArticlesByParams(String uuid, String slug, String authorName) {
        String baseSql = "SELECT * FROM %s WHERE 1=1".formatted(Tables.ARTICLES);
        MapSqlParameterSource params = new MapSqlParameterSource();

        StringBuilder sql = new StringBuilder(baseSql);
        if (uuid != null) {
            sql.append(" AND uuid = :uuid");
            params.addValue("uuid", uuid);
        }
        if (slug != null) {
            sql.append(" AND slug = :slug");
            params.addValue("slug", slug);
        }
        if (authorName != null) {
            sql.append(" AND author_id = :authorId");
            params.addValue("authorId", getUserIdByName(authorName));
        }

        List<ArticleBaseInfo> articleBaseInfos = namedParameterJdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(ArticleBaseInfo.class));

        List<Article> articles = new ArrayList<>();
        articleBaseInfos.forEach(baseInfo -> {
            UserBaseInfo userBaseInfo = getUserBaseInfoById(baseInfo.authorId);
            articles.add(
                    Article.builder()
                            .uuid(baseInfo.uuid)
                            .slug(baseInfo.slug)
                            .createdAt(baseInfo.createdAt)
                            .updatedAt(baseInfo.updatedAt)
                            .author(
                                    Author.builder()
                                            .username(userBaseInfo.username)
                                            .nickname(userBaseInfo.nickname)
                                            .build()
                            )
                            .build()
            );
        });

        return articles;
    }

    public Article getArticleByUuid(String uuid) {
        List<Article> list = getArticlesByParams(uuid, null, null);
        if (list.isEmpty()) {
            return null;
        }
        return list.getFirst();
    }

    public BigInteger getUserIdByName(String username) {
        String sql = "SELECT id FROM %s WHERE username = ?".formatted(UserBaseTableFields.USERNAME);
        BigInteger userId;
        try {
            userId = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BigInteger.class), username);
        } catch (EmptyResultDataAccessException e) {
            userId = BigInteger.ZERO;
            return userId;
        }

        return userId;
    }

    public String getUsernameById(BigInteger id) {
        String sql = "SELECT username FROM %s WHERE id = ?".formatted(Tables.USERS);
        String username;
        try {
            username = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(String.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return username;
    }

    public UserBaseInfo getUserBaseInfoById(BigInteger id) {
        String sql = "SELECT * FROM %s WHERE id = ?".formatted(Tables.USERS);
        UserBaseInfo userBaseInfo;
        try {
            userBaseInfo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserBaseInfo.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return userBaseInfo;
    }

//    public List<String> getArticleIdsByParams()
}
