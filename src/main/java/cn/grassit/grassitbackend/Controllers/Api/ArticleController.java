package cn.grassit.grassitbackend.Controllers.Api;

import cn.grassit.grassitbackend.Database.Api.DBTools;
import cn.grassit.grassitbackend.Format.Patterns;
import cn.grassit.grassitbackend.Response.ArticleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private DBTools dbTools;

    @GetMapping("/test/{content}")
    public String test(@PathVariable String content) {
        return "Test: " + content;
    }

    @GetMapping("/article/{identifier}")
    public ResponseEntity<ArticleDetail> getArticleDetail(@PathVariable String identifier) {
        // check if identifier is valid UUID
        if (!Patterns.UUID.matcher(identifier).matches()) {
            return ResponseEntity.notFound().build();
        }

        ArticleDetail articleDetail = dbTools.getArticleByUuid(identifier);

        return ResponseEntity.ok(articleDetail);
    }
}
