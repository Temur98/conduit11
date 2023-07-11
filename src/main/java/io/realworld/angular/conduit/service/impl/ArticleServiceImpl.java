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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;


    @Override
    public ResponseEntity<CommonResponse<List<ArticleDTO>>> getAllArticles(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {
        List<ArticleDTO> allArticles = articleRepository.getAllArticles(limit, offset, author, favorited, tag);
        System.out.println(allArticles);
        CommonResponse<List<ArticleDTO>> commonResponse = new CommonResponse<List<ArticleDTO>>();
        commonResponse.add("articles",allArticles);
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
        articleRepository.findById(articleDTO.id()).orElseThrow(() -> new NotFoundException("Article not found"));
        Article save = articleRepository.save(articleMapper.toEntity(articleDTO));
        return ResponseEntity.ok(articleMapper.toDto(save,articleRepository,userRepository));
    }

    @Override
    public ResponseEntity<ArticleDTO> updateArticleBySlag(String slag, ArticleDTO articleDTO) {
        Long idBySlug = CommonService.getIdBySlug(slag);
        articleRepository.findById(idBySlug).orElseThrow(() -> new NotFoundException("Article not found"));
        Article save = articleRepository.save(articleMapper.toEntity(articleDTO));

        return ResponseEntity.ok(articleMapper.toDto(save,articleRepository,userRepository));
    }

    @Override
    public ResponseEntity<ArticleDTO> addFavorite(String slug) {
        Long idBySlug = CommonService.getIdBySlug(slug);
        Article article = articleRepository.findById(idBySlug).orElseThrow(() -> new NotFoundException("Article not found"));
        Long userId = 0L;
        Integer integer = articleRepository.addLike(idBySlug, userId);
        System.out.println(integer);

        return ResponseEntity.ok(articleMapper.toDto(article,articleRepository,userRepository));
    }

    @Override
    public void deleteFavorite(String slug) {
        Long idBySlug = CommonService.getIdBySlug(slug);
        Long userId = 0L;
        articleRepository.removeLike(idBySlug,userId);
    }

    @Override
    public void deleteArticle(String slug) {
        Long id = CommonService.getIdBySlug(slug);
        articleRepository.delete(articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found")));
    }
}
