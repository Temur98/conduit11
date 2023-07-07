package uz.najottalim.javan6.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.javan6.dto.articledto.ArticleResponse;
import uz.najottalim.javan6.dto.articledto.ArticlesDto;
import uz.najottalim.javan6.dto.commentdto.CommentsDto;

import java.util.Optional;

public interface ArticleService {
    ResponseEntity<ArticlesDto> getArticles(Integer limit, Integer offset, Optional<String> author,Optional<String> favorited,Optional<String> tag);

    ResponseEntity<ArticleResponse> getArticleBySlug(String slug);

    ResponseEntity<CommentsDto> getArticleComments(String slug);
}
