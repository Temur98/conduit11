package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.groups.OnCreated;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
@Slf4j
@RequiredArgsConstructor
public class ArticlesController {
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<ArticleDTO>>> getAllArticles(
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> offset,
            @RequestParam Optional<String> author,
            @RequestParam Optional<String> favorited,
            @RequestParam Optional<String> tag){

        return articleService.getAllArticles(limit, offset, author, favorited, tag);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleDTO> getArticleBySlag(@PathVariable String slug){
        return articleService.getArticleBySlag(slug);
    }

    @PutMapping("/{slug}")
    public ResponseEntity<ArticleDTO> updateArticleBySlag(@PathVariable String slug, @RequestBody ArticleDTO articleDTO){
        return articleService.updateArticleBySlag(slug, articleDTO);
    }

    @PostMapping("/{slug}/favorite")
    public ResponseEntity<ArticleDTO> addFavorite(@PathVariable String slug){
        return articleService.addFavorite(slug);
    }

    @DeleteMapping("/{slug}/favorite")
    public void deleteFavorite(@PathVariable String slug){
        articleService.deleteFavorite(slug);
    }


    @PostMapping
    @Validated(OnCreated.class)
    public ResponseEntity<Map<String,ArticleDTO>> addArticle( @RequestBody Map<String,ArticleDTO> articleMap){
        return articleService.addArticle(articleMap);
    }

    @PutMapping
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO articleDTO){
        return articleService.updateArticle(articleDTO);
    }

    @DeleteMapping("/{slug}")
    public void deleteArticle(@PathVariable String slug){
        articleService.deleteArticle(slug);
    }
}
