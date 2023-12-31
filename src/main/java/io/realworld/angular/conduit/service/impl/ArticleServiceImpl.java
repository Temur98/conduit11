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
import org.springframework.data.domain.PageRequest;
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
        PageRequest pageRequest = null;
        if(limit.isPresent() && offset.isPresent()) {
            pageRequest = PageRequest.of(offset.get() / limit.get(), limit.get());
            List<ArticleDTO> articleDTOList = articleRepository.findAll(pageRequest).stream().map(a -> articleMapper.toDto(a, articleRepository, userRepository)).toList();

            if (author.isPresent()) {
                articleDTOList = articleDTOList.stream().filter(articleDTO -> articleDTO.author().userName().equals(author.get())).toList();
            }

            if (tag.isPresent()) {
                articleDTOList = articleDTOList.stream().filter(articleDTO -> articleDTO.tagList().contains(tag)).toList();
            }
            if(favorited.isPresent()){
                articleDTOList = articleDTOList.stream().filter(ArticleDTO::favorited).toList();
            }
            CommonResponse<List<ArticleDTO>> response = new CommonResponse<>();
            response.add("articles", articleDTOList);
            return ResponseEntity.ok(response);

        }


        throw new NotFoundException("Article Not found");
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
