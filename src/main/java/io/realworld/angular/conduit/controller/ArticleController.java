package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping("/{slag}")
    public ResponseEntity<ArticleResponse> getById(@PathVariable String slag){
        return articleService.getById(slag);
    }
    @PostMapping
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody ArticleDTO articleDTO){
        return articleService.addArticle(articleDTO);
    }

    @PutMapping
    public ResponseEntity<ArticleResponse> updateArticle(@RequestBody ArticleDTO articleDTO){
        return articleService.updateArticle(articleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable Long id){
        return articleService.deleteArticle(id);
    }
}
