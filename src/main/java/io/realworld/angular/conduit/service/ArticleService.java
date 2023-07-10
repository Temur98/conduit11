package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ArticleListDTO;
import org.springframework.http.ResponseEntity;

public interface ArticleService {
    ResponseEntity<ArticleDTO> findById(Long id);
    ArticleListDTO findWithPagination(Integer limit, Integer offset);
    ResponseEntity<ArticleDTO> save(ArticleDTO articleDTO);
}
