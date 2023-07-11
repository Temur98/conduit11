package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.dto.responseList.CommentListDto;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("add-new-article")
    public ResponseDto<ArticleDto> addNewArticle(@RequestBody ArticleDto articleDto){
        return articleService.addNewArticle(articleDto);
    }
    @GetMapping("get-by-id")
    public ResponseDto<ArticleDto> getById(@RequestParam Long id){
        return articleService.getById(id);
    }
    @DeleteMapping("delete-by-id")
    public ResponseDto<ArticleDto> deleteById(@RequestParam Long id){
        return articleService.deleteById(id);
    }
    @PutMapping("edit")
    public ResponseDto<ArticleDto> edit(@RequestBody ArticleDto articleDto){
        return articleService.edit(articleDto);
    }
    @GetMapping("get-all-article")
    public ResponseDto<List<ArticleDto>> getAllArticle(){
        return articleService.getAllArticle();
    }
    @GetMapping()
    public ResponseEntity<ArticleDto> getArticles(@RequestParam Integer limit,
                                                  @RequestParam Integer offset,
                                                  @RequestParam Optional<String> author,
                                                  @RequestParam Optional<String> favorited,
                                                  @RequestParam Optional<String> tag){
        return articleService.getArticles(limit,offset,author,favorited,tag);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleResponse> getArticleBySlug(@PathVariable String slug){
        return articleService.getArticleBySlug(slug);
    }
    @GetMapping("/{slug}/comments")
    public ResponseEntity<CommentListDto> getArticleComments(@PathVariable String slug){
        return articleService.getArticleComments(slug);
    }

    @GetMapping("/feed")
    public ResponseEntity<ArticleDto> getArticlesByToken(@RequestParam Integer limit,
                                                          @RequestParam Integer offset){
        return articleService.getArticlesByToken(limit,offset);
    };

    @PostMapping("/")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody ArticleResponse articleResponse){
        return articleService.addArticle(articleResponse);
    }

    @PostMapping("/{slug}/favorite")
    public ResponseEntity<ArticleResponse> likeArticle(@PathVariable String slug){
        return articleService.likeArticle(slug);
    }
    @DeleteMapping("/{slug}/favorite")
    public ResponseEntity<ArticleResponse> deleteLike(@PathVariable String slug){
        return articleService.deleteLike(slug);
    }
    @PostMapping("/{slug}/comments")
    public ResponseEntity<CommentResponse> addComment(@PathVariable String slug, @RequestBody CommentResponse commentResponse){
        return articleService.addComment(slug,commentResponse);
    }

    @DeleteMapping("/{slug}/comments/{id}")
    public void deleteComment(@PathVariable String slug, @PathVariable Long id){
        articleService.deleteComment(slug,id);
    }

    @DeleteMapping("/{slug}")
    public void deleteArticle(@PathVariable String slug){
        articleService.deleteArticle(slug);
    }
}
