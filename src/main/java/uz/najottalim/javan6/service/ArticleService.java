package uz.najottalim.javan6.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.javan6.dto.articledto.ArticleResultDto;
import uz.najottalim.javan6.dto.articledto.ArticlesDto;
import uz.najottalim.javan6.dto.commentdto.CommentsDto;

public interface ArticleService {
    ResponseEntity<ArticlesDto> getArticles(Integer limit, Integer offset);

    ResponseEntity<ArticleResultDto> getArticleBySlug(String slug);

    ResponseEntity<CommentsDto> getArticleComments(String slug);
}
