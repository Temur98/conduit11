package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.CommonResponse;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.ArticleMapper;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.TagRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;


    @Override
    public ResponseEntity<CommonResponse<List<ArticleDTO>>> getAllArticles(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {
        if (limit.isPresent() && offset.isPresent()) {
            List<Article> allArticles = articleRepository.getArticlesPageable(limit.get(), offset.get(), author, favorited, tag);

            List<ArticleDTO> articles = allArticles.stream().map(articleMapper::toDto).toList();

            CommonResponse commonResponse = new CommonResponse<>();
            commonResponse.add("articles", articles);
            commonResponse.add("articlesCount", articles.size());
            return ResponseEntity.ok(commonResponse);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<CommonResponse<ArticleDTO>> getArticleBySlag(String slug) {
        Long id = CommonService.getIdBySlug(slug);
        Article article = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found"));
        ArticleDTO dto = articleMapper.toDto(article);
        CommonResponse<ArticleDTO> commonResponse = new CommonResponse<>();
        commonResponse.add("article",dto);
        return ResponseEntity.ok(commonResponse);
    }

    @Override
    public ResponseEntity<Map<String,ArticleDTO>> addArticle(Map<String,ArticleDTO> articleMap) {
        ArticleDTO articleDTO = articleMap.get("article");

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Article entity = articleMapper.toEntity(articleDTO);
        entity.setAuthor(userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found")));
        entity.setCreatedAt(LocalDate.now());
        System.out.println(entity);
        Article save = articleRepository.save(entity);
        save.setTagList(entity.getTagList());
        return ResponseEntity.ok(Map.of("article", articleMapper.toDto(save)));
    }

    @Override
    public ResponseEntity<ArticleDTO> updateArticle(ArticleDTO articleDTO) {
        articleRepository.findById(articleDTO.getId()).orElseThrow(() -> new NotFoundException("Article not found"));
        articleDTO.setUpdateAt(LocalDate.now());
        Article save = articleRepository.save(articleMapper.toEntity(articleDTO));
        return ResponseEntity.ok(articleMapper.toDto(save));
    }

    @Override
    public ResponseEntity<ArticleDTO> updateArticleBySlag(String slag, ArticleDTO articleDTO) {
        Long idBySlug = CommonService.getIdBySlug(slag);
        articleRepository.findById(idBySlug).orElseThrow(() -> new NotFoundException("Article not found"));
        Article save = articleRepository.save(articleMapper.toEntity(articleDTO));

        return ResponseEntity.ok(articleMapper.toDto(save));
    }

    @Override
    public ResponseEntity<ArticleDTO> addFavorite(String slug) {
        Long idBySlug = CommonService.getIdBySlug(slug);
        Article article = articleRepository.findById(idBySlug).orElseThrow(() -> new NotFoundException("Article not found"));
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(name).orElseThrow(() -> new NotFoundException("User not found"));
        articleRepository.addLike(idBySlug, user.getId());
        return ResponseEntity.ok(articleMapper.toDto(article));
    }

    @Override
    public void deleteFavorite(String slug,Principal principal) {
        Long idBySlug = CommonService.getIdBySlug(slug);
        Long userId = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("User not found")).getId();
        articleRepository.removeLike(idBySlug,userId);
    }

    @Override
    public void deleteArticle(String slug) {
        Long id = CommonService.getIdBySlug(slug);
        articleRepository.delete(articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found")));
    }

    @Override
    public ResponseEntity<CommonResponse<ArticleDTO>> getOwnUserArticleByPagination(Optional<Integer> limit, Optional<Integer> offset) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(name).orElseThrow(() -> new NotFoundException("User not found"));
        PageRequest pageRequest = null;
        if (limit.isPresent() && offset.isPresent()){
            pageRequest = PageRequest.of(offset.get() / limit.get(), limit.get());
        }
        List<Article> articleByAuthorId = articleRepository.findArticleByAuthorId(user.getId(), pageRequest);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.add("articles",articleByAuthorId.stream().map(articleMapper::toDto).toList());
        commonResponse.add("articlesCount",articleByAuthorId.size());
        return ResponseEntity.ok(commonResponse);
    }
}
