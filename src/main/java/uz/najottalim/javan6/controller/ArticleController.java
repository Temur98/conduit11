package uz.najottalim.javan6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.javan6.dto.articledto.ArticleResponse;
import uz.najottalim.javan6.dto.articledto.ArticlesDto;
import uz.najottalim.javan6.dto.commentdto.CommentsDto;
import uz.najottalim.javan6.service.ArticleService;

import java.util.Optional;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping()
    public ResponseEntity<ArticlesDto> getArticles(@RequestParam Integer limit,
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
    public ResponseEntity<CommentsDto> getArticleComments(@PathVariable String slug){
        return articleService.getArticleComments(slug);
    }
}
