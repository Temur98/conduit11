package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
