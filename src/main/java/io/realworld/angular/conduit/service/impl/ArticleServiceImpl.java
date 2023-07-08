package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.service.ArticleService;
import io.realworld.angular.conduit.service.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ResponseDto<ArticleDto> addNewArticle(ArticleDto articleDto) {
        try {
            return ResponseDto.<ArticleDto>builder()
                    .code(0)
                    .success(true)
                    .message("OK")
                    .data(articleMapper.toDto(articleRepository.save(articleMapper.toEntity(articleDto))))
                    .build();
        }catch (Exception e) {
            return ResponseDto.<ArticleDto>builder()
                    .code(1)
                    .success(false)
                    .message("Database Error")
                    .data(articleDto)
                    .build();
        }
    }

    @Override
    public ResponseDto<ArticleDto> getById(Long id) {
        return articleRepository.findById(id)
                .map(article -> ResponseDto.<ArticleDto>builder()
                        .code(0)
                        .success(true)
                        .message("OK")
                        .data(articleMapper.toDto(article))
                        .build())
                .orElse(ResponseDto.<ArticleDto>builder()
                        .code(-1)
                        .success(false)
                        .message("Not Found")
                        .build());
    }

    @Override
    public ResponseDto<ArticleDto> deleteById(Long id) {
        return articleRepository.findById(id)
                .map(article -> {
                    articleRepository.deleteById(id);
                    return ResponseDto.<ArticleDto>builder()
                            .code(0)
                            .success(true)
                            .message("OK")
                            .data(articleMapper.toDto(article))
                            .build();
                })
                .orElse(ResponseDto.<ArticleDto>builder()
                        .code(-1)
                        .success(false)
                        .message("Not Found")
                        .build());
    }

    @Override
    public ResponseDto<ArticleDto> edit(ArticleDto articleDto) {
        if (articleDto.getId() == null) {
            return ResponseDto.<ArticleDto>builder()
                    .code(-2)
                    .success(false)
                    .message("Validation Error")
                    .data(articleDto)
                    .build();
        }
        Optional<Article> optionalArticle = articleRepository.findById(articleDto.getId());
        if (optionalArticle.isEmpty()) {
            return ResponseDto.<ArticleDto>builder()
                    .code(-1)
                    .success(false)
                    .message("Not Found")
                    .data(articleDto)
                    .build();
        }
        Article article = optionalArticle.get();
        if (articleDto.getBody() != null) {
            article.setBody(article.getBody());
        }
        if (articleDto.getDescription() != null) {
            article.setDescription(article.getDescription());
        }
        if (articleDto.getTags() != null) {
            article.setTags(article.getTags());
        }
        if (articleDto.getTitle() != null) {
            article.setTitle(article.getTitle());
        }
        if (articleDto.getUpdatedAt() != null) {
            article.setUpdateAt(LocalDateTime.now());
        }
        try {
            articleRepository.save(article);
            return ResponseDto.<ArticleDto>builder()
                    .code(0)
                    .success(true)
                    .message("OK")
                    .data(articleMapper.toDto(article))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ArticleDto>builder()
                    .code(1)
                    .success(false)
                    .message("Database Error")
                    .data(articleDto)
                    .build();
        }
    }

    @Override
    public ResponseDto<List<ArticleDto>> getAllArticle() {
        return ResponseDto.<List<ArticleDto>>builder()
                .code(0)
                .success(true)
                .message("OK")
                .data(articleRepository.findAll().stream().map(articleMapper::toDto).toList())
                .build();
    }
}
