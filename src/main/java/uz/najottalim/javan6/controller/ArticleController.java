package uz.najottalim.javan6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.javan6.dto.articledto.ArticleResultDto;
import uz.najottalim.javan6.dto.articledto.ArticlesDto;
import uz.najottalim.javan6.dto.commentdto.CommentsDto;
import uz.najottalim.javan6.service.ArticleService;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping()
    public ResponseEntity<ArticlesDto> getArticles(@RequestParam Integer limit,
                                                   @RequestParam Integer offset){
        return articleService.getArticles(limit,offset);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleResultDto> getArticleBySlug(@PathVariable String slug){
        return articleService.getArticleBySlug(slug);
    }
    @GetMapping("/{slug}/comments")
    public ResponseEntity<CommentsDto> getArticleComments(@PathVariable String slug){
        return articleService.getArticleComments(slug);
    }
}
