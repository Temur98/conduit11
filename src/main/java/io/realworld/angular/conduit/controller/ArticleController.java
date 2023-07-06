package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.Service.ArticleService;
import io.realworld.angular.conduit.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ArticleDto>> getArticlesByUserId(@PathVariable Long userId){
        return articleService.getUserArticlesByUserId(userId);
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<ArticleDto>> getFavoritedArticlesByUserId(@PathVariable Long userId){
        return articleService.getUsersFavoritedArticlesById(userId);
    }
    @GetMapping("/{slug}")
    ResponseEntity<ArticleDto> getArticleBySlug(@PathVariable String slug){
        return articleService.getArticleBySlug(slug);
    }

    @PutMapping
    ResponseEntity<ArticleDto> updateArticle(@PathVariable String slug,@RequestBody ArticleDto articleDto){
        return articleService.updateArticle(slug,articleDto);
    }

    @PostMapping
    ResponseEntity<ArticleDto> addArticle(@RequestBody ArticleDto articleDto){
        return articleService.addArticle(articleDto);
    }
    @DeleteMapping("/{slug}")
    ResponseEntity<ArticleDto> deleteArticle(@PathVariable String slug){
        return articleService.deleteArticle(slug);
    }

}
