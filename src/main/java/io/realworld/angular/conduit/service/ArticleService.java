package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ArticleDTO;
import org.springframework.http.ResponseEntity;

public interface ArticleService {
    ResponseEntity<ArticleDTO> getArticles(Integer limit, Integer offset);
}
