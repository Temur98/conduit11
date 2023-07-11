package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.ArticleWithOwtDto;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.repository.extension.ArticleRepositoryExtension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ArticleRepositoryExtensionImpl implements ArticleRepositoryExtension {
    private final ArticleWithOwtDto articleMapper;
    private final UserRepository userRepository;
    private final EntityManager entityManager;


    @Override
    public List<ArticleDTO> getAllArticles(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {
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
                .map(articleMapper::toDTO)
                .toList();
    }
}
