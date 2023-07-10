package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ArticleListDTO;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;


    @Override
    public ResponseEntity<ArticleDTO> findById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found"));
        ArticleDTO dto = articleMapper.toDto(article);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ArticleListDTO findWithPagination(Integer limit, Integer offset) {
        PageRequest pageRequest = PageRequest.of(offset/limit,limit, Sort.by("createdat"));
        List<Article> content = articleRepository.findAll(pageRequest).getContent();
        ArticleListDTO articleListDTO = new ArticleListDTO();
        articleListDTO.setArticles(content.stream().map(articleMapper::toDto).toList());
        articleListDTO.setSize(content.size());
        return articleListDTO;
    }

    @Override
    public ResponseEntity<ArticleDTO> save(ArticleDTO articleDTO) {
        Article save = articleRepository.save(articleMapper.toEntity(articleDTO));
        save.setUser(userRepository.findByUsername("default").orElseThrow(() -> new NotFoundException("User not found")));
        return ResponseEntity.ok(articleMapper.toDto(save));
    }
}
