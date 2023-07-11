package io.realworld.angular.conduit.repository.ext.impl;

import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Profile;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.repository.ext.ArticleRepositoryExtension;
import io.realworld.angular.conduit.service.mapper.ArticleMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleRepositoryExtensionImpl implements ArticleRepositoryExtension {
    private final EntityManager entityManager;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository;
    public List<ArticleDto> getArticleListPageable(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> query = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = query.from(Article.class);
        List<Predicate> predicates = new ArrayList<>();

        author.ifPresent(auth -> predicates.add(criteriaBuilder.equal(
                root.get("author"),
                userRepository.findByUsername(auth).orElseThrow(() -> new NotFoundException("User not found"))
        )));

        favorited.ifPresent(fav -> {
            List<Predicate> likePredicate = new ArrayList<>();
            Long userId = userRepository.findByUsername(fav).orElseThrow(() -> new NotFoundException("User not found")).getId();

            Query query1 = entityManager.createNativeQuery("SELECT ARTICLE_ID FROM LIKES WHERE USER_ID = ?1", Object.class).setParameter(1, userId);
            List<Long> resultList = (List<Long>) query1.getResultList();

            resultList.forEach(id -> likePredicate.add(criteriaBuilder.equal(
                            root.get("id"),
                            id
                    ))
            );
            predicates.add(criteriaBuilder.or(likePredicate.toArray(Predicate[]::new)));
        });

        query.select(root).where(criteriaBuilder.or(predicates.toArray(Predicate[]::new)));

        TypedQuery<Article> typedQuery = entityManager.createQuery(query);

        if (limit.isPresent() && offset.isPresent()){
            typedQuery.setFirstResult(offset.get())
                    .setMaxResults(limit.get()).getResultList();
        }


        return typedQuery.getResultList()
                .stream()
                .map(articleMapper::toDto)
                .toList();
    }
}
