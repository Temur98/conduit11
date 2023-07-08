package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticleService {
    ResponseDto<ArticleDto> addNewArticle(ArticleDto articleDto);

    ResponseDto<ArticleDto> getById(Long id);

    ResponseDto<ArticleDto> deleteById(Long id);

    ResponseDto<ArticleDto> edit(ArticleDto articleDto);

    ResponseDto<List<ArticleDto>> getAllArticle();
}
