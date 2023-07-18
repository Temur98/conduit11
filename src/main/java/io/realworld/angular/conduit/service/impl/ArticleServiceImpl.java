package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.dto.responseList.ArticleListDto;
import io.realworld.angular.conduit.dto.responseList.CommentListDto;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Comment;
import io.realworld.angular.conduit.model.Profile;
import io.realworld.angular.conduit.model.Users;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.CommentRepository;
import io.realworld.angular.conduit.repository.ProfileRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ArticleService;
import io.realworld.angular.conduit.service.mapper.ArticleMapper;
import io.realworld.angular.conduit.service.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public ResponseEntity<ArticleListDto> getArticles(Integer limit, Integer offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {
        return ResponseEntity.ok(
                new ArticleListDto(
                        articleRepository.getArticleListPageable(limit,offset,author,favorited,tag)
                                .stream().map(articleMapper::toDto).collect(Collectors.toList())
                )
        );
    }

    @Override
    public ResponseEntity<ArticleResponse> getArticleBySlug(String slug) {
        Long id = Long.parseLong(slug.substring(slug.lastIndexOf("-") + 1));
        return ResponseEntity.ok(new ArticleResponse(
                articleMapper.toDto(articleRepository.findById(id).orElseThrow())
        ));
    }

    @Override
    public ResponseEntity<CommentListDto> getArticleComments(String slug) {
        Long id = Long.parseLong(slug.substring(slug.lastIndexOf("-") + 1));
        return ResponseEntity.ok(new CommentListDto(
                commentRepository.findAllByProfileId(id).stream().map(commentMapper::toDto).toList()
        ));
    }

    @Override
    public ResponseEntity<ArticleListDto> getArticlesByToken(Integer limit, Integer offset) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Profile user = profileRepository.findByUserEmail(authentication.getName())
                .orElseThrow();
        List<Article> articlesByFollower = articleRepository.getArticlesByFollower(user.getId(), limit, offset);
        return ResponseEntity.ok(new ArticleListDto(articlesByFollower
                .stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList()))
        );
    }

    @Override
    public ResponseEntity<ArticleResponse> addArticle(ArticleResponse articleResponse) {
        return ResponseEntity.ok(new ArticleResponse(articleMapper.toDto(articleRepository.save(
                articleMapper.toEntity(articleResponse.getArticle())
        ))));
    }

    @Override
    public ResponseEntity<ArticleResponse> likeArticle(String slug) {
        Long id = Long.parseLong(slug.substring(slug.lastIndexOf("-") + 1));
        Article article = articleRepository.findById(id)
                .orElseThrow();
        Users users = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow();
        if (!articleRepository.isFavorited(id, users.getId())) {
            articleRepository.likeArticle(id, users.getId());
        }
        return ResponseEntity.ok(new ArticleResponse(
                articleMapper.toDto(article)
        ));
    }

    @Override
    public ResponseEntity<ArticleResponse> deleteLike(String slug) {
        Long id = Long.parseLong(slug.substring(slug.lastIndexOf("-") + 1));
        Article article = articleRepository.findById(id)
                .orElseThrow();
        Users users = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow();
        articleRepository.deleteLike(users.getId(), id);
        return ResponseEntity.ok(new ArticleResponse(
                articleMapper.toDto(article)
        ));
    }

    @Override
    public ResponseEntity<CommentResponse> addComment(String slug, CommentResponse commentResponse) {
        Long id = Long.parseLong(slug.substring(slug.lastIndexOf("-") + 1));
        return ResponseEntity.ok(new CommentResponse(
                commentMapper.toDto(commentRepository.save(new Comment(
                        commentResponse.getComment().getId(),
                        commentResponse.getComment().getBody(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        profileRepository.findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(),
                        articleRepository.findById(id).orElseThrow()
                )))
        ));
    }

    @Override
    public void deleteComment(String slug, Long id) {
        Long articleId = Long.parseLong(slug.substring(slug.lastIndexOf("-") + 1));
        commentRepository.delete(commentRepository.findByIdAndArticleId(id, articleId).orElseThrow());
    }

    @Override
    public void deleteArticle(String slug) {
        Long articleId = Long.parseLong(slug.substring(slug.lastIndexOf("-") + 1));
        articleRepository.findById(articleId).orElseThrow();
        articleRepository.deleteById(articleId);
    }

    @Override
    public ResponseEntity<ArticleResponse> updateArticle(String slug, ArticleResponse articleResponse) {
        Long id = Long.parseLong(slug.substring(slug.lastIndexOf("-")+1));
        articleRepository.findById(id)
                .orElseThrow();
        articleResponse.getArticle().setId(id);
        Profile profile = profileRepository.findByUserUsername(articleResponse.getArticle().getProfileDto().getUsername())
                .orElseThrow();
        Article save = articleRepository.save(articleMapper.toEntity(articleResponse.getArticle()));

        return ResponseEntity.ok(new ArticleResponse(articleMapper.toDto(save)));
    }

//    @Override
//    public ResponseDto<ArticleDto> addNewArticle(ArticleDto articleDto) {
//        try {
//            return ResponseDto.<ArticleDto>builder()
//                    .code(0)
//                    .success(true)
//                    .message("OK")
//                    .data(articleMapper.toDto(articleRepository.save(articleMapper.toEntity(articleDto))))
//                    .build();
//        }catch (Exception e) {
//            return ResponseDto.<ArticleDto>builder()
//                    .code(1)
//                    .success(false)
//                    .message("Database Error")
//                    .data(articleDto)
//                    .build();
//        }
//    }
//
//    @Override
//    public ResponseDto<ArticleDto> getById(Long id) {
//        return articleRepository.findById(id)
//                .map(article -> ResponseDto.<ArticleDto>builder()
//                        .code(0)
//                        .success(true)
//                        .message("OK")
//                        .data(articleMapper.toDto(article))
//                        .build())
//                .orElse(ResponseDto.<ArticleDto>builder()
//                        .code(-1)
//                        .success(false)
//                        .message("Not Found")
//                        .build());
//    }
//
//    @Override
//    public ResponseDto<ArticleDto> deleteById(Long id) {
//        return articleRepository.findById(id)
//                .map(article -> {
//                    articleRepository.deleteById(id);
//                    return ResponseDto.<ArticleDto>builder()
//                            .code(0)
//                            .success(true)
//                            .message("OK")
//                            .data(articleMapper.toDto(article))
//                            .build();
//                })
//                .orElse(ResponseDto.<ArticleDto>builder()
//                        .code(-1)
//                        .success(false)
//                        .message("Not Found")
//                        .build());
//    }
//
//    @Override
//    public ResponseDto<ArticleDto> edit(ArticleDto articleDto) {
//        if (articleDto.getId() == null) {
//            return ResponseDto.<ArticleDto>builder()
//                    .code(-2)
//                    .success(false)
//                    .message("Validation Error")
//                    .data(articleDto)
//                    .build();
//        }
//        Optional<Article> optionalArticle = articleRepository.findById(articleDto.getId());
//        if (optionalArticle.isEmpty()) {
//            return ResponseDto.<ArticleDto>builder()
//                    .code(-1)
//                    .success(false)
//                    .message("Not Found")
//                    .data(articleDto)
//                    .build();
//        }
//        Article article = optionalArticle.get();
//        if (articleDto.getBody() != null) {
//            article.setBody(article.getBody());
//        }
//        if (articleDto.getDescription() != null) {
//            article.setDescription(article.getDescription());
//        }
//        if (articleDto.getTags() != null) {
//            article.setTags(article.getTags());
//        }
//        if (articleDto.getTitle() != null) {
//            article.setTitle(article.getTitle());
//        }
//        if (articleDto.getUpdatedAt() != null) {
//            article.setUpdateAt(LocalDateTime.now());
//        }
//        try {
//            articleRepository.save(article);
//            return ResponseDto.<ArticleDto>builder()
//                    .code(0)
//                    .success(true)
//                    .message("OK")
//                    .data(articleMapper.toDto(article))
//                    .build();
//        } catch (Exception e) {
//            return ResponseDto.<ArticleDto>builder()
//                    .code(1)
//                    .success(false)
//                    .message("Database Error")
//                    .data(articleDto)
//                    .build();
//        }
//    }
//
//    @Override
//    public ResponseDto<List<ArticleDto>> getAllArticle() {
//        return ResponseDto.<List<ArticleDto>>builder()
//                .code(0)
//                .success(true)
//                .message("OK")
//                .data(articleRepository.findAll().stream().map(articleMapper::toDto).toList())
//                .build();
//    }
}
