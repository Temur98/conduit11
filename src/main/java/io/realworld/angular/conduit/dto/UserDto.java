package io.realworld.angular.conduit.dto;

import io.realworld.angular.conduit.model.Article;
import java.util.List;

public record UserDto (
        Long id,
        String username,
        String bio,
        String email,
        List<Article> myArticles,
        List<Article> favoritedArticle,
        String image
){

}
