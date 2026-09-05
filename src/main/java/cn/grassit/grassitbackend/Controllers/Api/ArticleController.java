package cn.grassit.grassitbackend.Controllers.Api;

import cn.grassit.grassitbackend.Database.Api.DBTools;
import cn.grassit.grassitbackend.Format.Patterns;
import cn.grassit.grassitbackend.Response.Article;
import cn.grassit.grassitbackend.Services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticleController {

    private final DBTools dbTools;
    private final FileService fileService;
    
    @GetMapping("/article")
    public ResponseEntity<List<JacksonProperties.Json>> getArticlesByParams(
            @RequestParam(name = "author", required = false) BigInteger authorId,
            @RequestParam(name = "slug", required = false) String slug,
            @RequestParam(name = "uuid", required = false) String uuid,
            @RequestParam(name = "start", required = false) LocalDate start,
            @RequestParam(name = "end", required = false) LocalDate end,
            @RequestParam(name = "tag", required = false) String tag
    ) {
        return null;
    }

    @GetMapping("/article/{identifier}")
    public ResponseEntity<Article> getArticleByIdentifier(@PathVariable String identifier) throws IOException {
        // check if identifier is valid UUID
        if (Patterns.UUID.matcher(identifier).matches()) {
            Article article = dbTools.getArticleByUuid(identifier);
            article.content = fileService.getArticleContent(article.uuid);
            return ResponseEntity.ok(article);
        }

        List<Article> list = dbTools.getArticlesByParams(null, identifier, null);
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Article article = list.getFirst();
        article.content = fileService.getArticleContent(article.uuid);

        return ResponseEntity.ok(article);
    }
}
