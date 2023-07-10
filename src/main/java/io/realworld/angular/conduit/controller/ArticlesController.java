package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/articles")
@Slf4j
@RequiredArgsConstructor
public class ArticlesController {
    private final ArticleService articleService;

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleResponse> getArticleBySlag(@PathVariable String slug){
        return articleService.getArticleBySlag(slug);
    }

    @PutMapping("/{slug}")
    public ResponseEntity<ArticleResponse> updateArticleBySlag(@PathVariable String slug, @RequestBody ArticleDTO articleDTO){
        return articleService.updateArticleBySlag(slug, articleDTO);
    }

    @PostMapping("/{slug}/favorite")
    public ResponseEntity<ArticleResponse> addFavorite(@PathVariable String slug){
        return articleService.addFavorite(slug);
    }

    @DeleteMapping("/{slug}/favorite")
    public void deleteFavorite(@PathVariable String slug){
        articleService.deleteFavorite(slug);
    }


    @PostMapping
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody ArticleDTO articleDTO){
        return articleService.addArticle(articleDTO);
    }

    @PutMapping
    public ResponseEntity<ArticleResponse> updateArticle(@RequestBody ArticleDTO articleDTO){
        return articleService.updateArticle(articleDTO);
    }

    @DeleteMapping("/{slug}")
    public void deleteArticle(@PathVariable String slug){
        articleService.deleteArticle(slug);
    }
}
