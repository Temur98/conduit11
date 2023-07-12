package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.ArticleMapper;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ArticleService;
import io.realworld.angular.conduit.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository;


    @Override
    public ResponseEntity<CommentResponse<List<ArticleDTO>>> getAllArticles(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {
//        PageRequest pageRequest = null;
//        if(limit.isPresent() && offset.isPresent()) {
//            pageRequest = PageRequest.of(offset.get() / limit.get(), limit.get());
//            List<ArticleDTO> articleDTOList = articleRepository.findAll(pageRequest).stream().map(a -> articleMapper.toDto(a, articleRepository, userRepository)).toList();
//
//            if (author.isPresent()) {
//                articleDTOList = articleDTOList.stream().filter(articleDTO -> articleDTO.author().userName().equals(author.get())).toList();
//            }
//
//            if (tag.isPresent()) {
//                articleDTOList = articleDTOList.stream().filter(articleDTO -> articleDTO.tagList().contains(tag)).toList();
//            }
//            if(favorited.isPresent()){
//                articleDTOList = articleDTOList.stream().filter(ArticleDTO::favorited).toList();
//            }
//            CommentResponse<List<ArticleDTO>> response = new CommentResponse<>();
//            response.add("articles", articleDTOList);
//            return ResponseEntity.ok(response);
//
//        }
//        throw new NotFoundException("Article Not found");

        List<ArticleDTO> allArticles = articleRepository.getAllArticles(limit, offset, author, favorited, tag);
        CommentResponse commonResponse = new CommentResponse<>();
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
