package uz.najottalim.javan6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.najottalim.javan6.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
}
