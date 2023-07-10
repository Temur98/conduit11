package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ArticleListDTO;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> findById(@PathVariable Long id){
        return articleService.findById(id);
    }
    @GetMapping
    public ResponseEntity<ArticleListDTO> findWithPagination(@RequestParam Optional<Integer> limit,@RequestParam Optional<Integer> offset){
        ArticleListDTO articleListDTO = null;
        if (limit.isPresent() && offset.isPresent()) {
            articleListDTO = articleService.findWithPagination(limit.get(),offset.get());
        } else {
            articleListDTO = articleService.findWithPagination(10,0);
        }
        return ResponseEntity.ok(articleListDTO);
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> save(@RequestBody ArticleDTO articleDTO){
        return articleService.save(articleDTO);
    }
}
