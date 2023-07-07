package uz.najottalim.javan6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.customexseptions.NoResourceFoundException;
import uz.najottalim.javan6.dto.articledto.ArticleResponse;
import uz.najottalim.javan6.dto.articledto.ArticlesDto;
import uz.najottalim.javan6.dto.commentdto.CommentsDto;
import uz.najottalim.javan6.entity.Comment;
import uz.najottalim.javan6.repository.ArticleRepository;
import uz.najottalim.javan6.repository.CommentRepository;
import uz.najottalim.javan6.service.ArticleService;
import uz.najottalim.javan6.service.mapper.ArticleMapper;
import uz.najottalim.javan6.service.mapper.CommentMapper;

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
    @Override
    public ResponseEntity<ArticlesDto> getArticles(Integer limit, Integer offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {

        return ResponseEntity.ok(
                new ArticlesDto(
                        articleRepository.getArticlesPageable(limit,offset,author,favorited,tag)
                                .stream().map(articleMapper::toDto).collect(Collectors.toList())
                )
        );
    }

    @Override
    public ResponseEntity<ArticleResponse> getArticleBySlug(String slug) {
        String[] split = slug.split("-");
        Long id = Long.parseLong(split[split.length-1]);
        return ResponseEntity.ok(new ArticleResponse(
         articleMapper.toDto(
                 articleRepository.findById(id).orElseThrow(()->new NoResourceFoundException("Article not found"))
         )
        ));
    }

    @Override
    public ResponseEntity<CommentsDto> getArticleComments(String slug) {
        String[] split = slug.split("-");
        Long id = Long.parseLong(split[split.length-1]);
        List<Comment> comments = commentRepository.findAllByProfileId(id);
        return ResponseEntity.ok(new CommentsDto(
                comments.stream().map(commentMapper::toDto).collect(Collectors.toList())
        ));
    }
}
