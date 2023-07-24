package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.controller.create.Create;
import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;


@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping()
    public ResponseEntity<CommentResponse<List<ArticleDTO>>> getAllArticles(
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> offset,
            @RequestParam Optional<String> author,
            @RequestParam Optional<String> favorited,
            @RequestParam Optional<String> tag
    ){
        return articleService.getAllArticles(limit, offset, author, favorited, tag);
    }

    @PutMapping("/{slug}")
    public ResponseEntity<ArticleDTO> updateArticleBySlag(@PathVariable String slug, @RequestBody ArticleDTO articleDTO){
        return articleService.updateArticleBySlag(slug, articleDTO);
    }
    @DeleteMapping("/{slug}")
    public void deleteArticle(@PathVariable String slug){
        articleService.deleteArticle(slug);
    }
    @PostMapping("/")
    @Validated(Create.class)
    public ResponseEntity<Map<String,ArticleDTO>> addArticle(@Valid @RequestBody Map<String,ArticleDTO> articleMap){
        return articleService.addArticle(articleMap);
    }

    @PutMapping
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO articleDTO){
        return articleService.updateArticle(articleDTO);
    }
    @PostMapping("/{slug}/favorite")
    public ResponseEntity<ArticleDTO> addFavorite(@PathVariable String slug){
        return articleService.addFavorite(slug);
    }
    @DeleteMapping("/{slug}/favorite")
    public void deleteFavorite(@PathVariable String slug, Principal principal){
        articleService.deleteFavorite(slug,principal);
    }


}
