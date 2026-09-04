package cn.grassit.grassitbackend.Services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileService {

    @Value("${application.data-folder}")
    private String dataRoot;
    private String articlesRoot;
    private String resourcesRoot;

    @PostConstruct
    public void initialize() throws IOException {
        Path dataFolder = Path.of(dataRoot);
        if (!Files.exists(dataFolder)) {
            Files.createDirectories(dataFolder);
        }

        Path articlesFolder = Path.of(dataRoot).resolve("articles");
        articlesRoot = articlesFolder.toString();
        if (!Files.exists(articlesFolder)) {
            Files.createDirectories(articlesFolder);
        }

        Path resourcesFolder = Path.of(dataRoot).resolve("resources");
        resourcesRoot = resourcesFolder.toString();
        if (!Files.exists(resourcesFolder)) {
            Files.createDirectories(resourcesFolder);
        }
    }

    public void createArticle(String uuid, String content) throws IOException {
        Path path = Path.of(articlesRoot).resolve(uuid).resolve(uuid + ".md");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        Files.writeString(path, content);
    }

    public String getArticleContent(String uuid) throws IOException {
        Path path = Path.of(articlesRoot).resolve(uuid).resolve(uuid + ".md");
        if (!Files.exists(path)) {
            return null;
        }
        return Files.readString(path);
    }

}
