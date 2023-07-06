package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.service.ArticleService;
import org.springframework.http.ResponseEntity;

public class ArticleServiceImpl implements ArticleService {
    @Override
    public ResponseEntity<ArticleDTO> getArticles(Integer limit, Integer offset) {
        return null;
    }
}
