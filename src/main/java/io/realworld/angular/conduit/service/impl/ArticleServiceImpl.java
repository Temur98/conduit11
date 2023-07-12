package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.ArticleMapper;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;


    @Override
    public ResponseEntity<CommonResponse<List<ArticleDTO>>> getAllArticles(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {
        List<Article> allArticles = articleRepository.getAllArticles(limit, offset, author, favorited, tag);
        CommonResponse commonResponse = new CommonResponse<>();
        commonResponse.add("articles", allArticles);
        return ResponseEntity.ok(commonResponse);
    }



    @Override
    public ResponseEntity<ArticleDTO> getArticleBySlag(String slug) {
        Long id = CommonService.getIdBySlug(slug);
        Article article = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found"));
        ArticleDTO dto = articleMapper.toDto(article, articleRepository, userRepository);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ArticleDTO> addArticle(ArticleDTO articleDTO) {
        Article save = articleRepository.save(articleMapper.toEntity(articleDTO));
        return ResponseEntity.ok(articleMapper.toDto(save, articleRepository, userRepository));
    }

    @Override
    public ResponseEntity<ArticleDTO> updateArticle(ArticleDTO articleDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleDTO> updateArticleBySlag(String slag, ArticleDTO articleDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleDTO> addFavorite(String slug) {
        return null;
    }

    @Override
    public void deleteFavorite(String slug) {

    }

    @Override
    public void deleteArticle(String slug) {

    }
}
