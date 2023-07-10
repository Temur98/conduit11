package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping()
    public ResponseEntity<ArticleResponse> getArticles(@RequestParam Integer limit,
                                                       @RequestParam Integer offset,
                                                       @RequestParam Optional<String> author,
                                                       @RequestParam Optional<String> favorited,
                                                       @RequestParam Optional<String> tag){
        return articleService.getArticles(limit,offset,author,favorited,tag);
    }
    @GetMapping("/{slug}")
    public ResponseEntity<ArticleResponse> getById(@PathVariable String slug){
        return articleService.getById(slug);
    }
    @PostMapping
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody ArticleDTO articleDTO){
        return articleService.addArticle(articleDTO);
    }
    @GetMapping("/{slug}/comments")
    public ResponseEntity<ArticleResponse> getArticleComments(@PathVariable String slug){
        return articleService.getArticleComments(slug);
    }
    @PutMapping
    public ResponseEntity<ArticleResponse> updateArticle(@RequestBody ArticleDTO articleDTO){
        return articleService.updateArticle(articleDTO);
    }
    @DeleteMapping("/{slug}/favorite")
    public void deleteFavorite(@PathVariable Long slug){
        articleService.deleteArticle(slug); //ishi bor long emas string kelishi kerak
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable Long id){
        return articleService.deleteArticle(id);
    }
}
