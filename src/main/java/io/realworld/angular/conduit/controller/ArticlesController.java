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
    @GetMapping()
    public ResponseEntity<ArticleResponse> getArticles(@RequestParam Integer limit,
                                                       @RequestParam Integer offset,
                                                       @RequestParam Optional<String> author,
                                                       @RequestParam Optional<String> favorited,
                                                       @RequestParam Optional<String> tag){
        return articleService.getArticles(limit,offset,author,favorited,tag);
    }
    @GetMapping("/{slag}")
    public ResponseEntity<ArticleResponse> getArticleBySlag(@PathVariable String slag){
        return articleService.getArticleBySlag(slag);
    }

    @GetMapping("/{slag}/comments")
    public ResponseEntity<ArticleResponse> getArticleComments(@PathVariable String slag){
        return articleService.getArticleComments(slag);
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
