package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.mapper.UserMapper;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ArticleRepository articleRepository;
    @Override
    public ResponseEntity<ProfileDTO> getProfileByUsername(String username) {
        Optional<User> profile = userRepository.findByUsername(username);
        return ResponseEntity.ok(userMapper.toProfileDto(profile.get()));
    }

    @Override
    public ResponseEntity<ProfileDTO> followToProfile(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Article> article = articleRepository.findById(user.get().getId());
        Boolean followedToArticleOwner = userRepository.isFollowedToArticleOwner(user.get().getId(), article.get().getAuthor().getId());
        return followedToArticleOwner ? ResponseEntity.ok(userMapper.toProfileDto(user.get())) : null;

    }

    @Override
    public ResponseEntity<ProfileDTO> unfollowFromProfile(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Article> article = articleRepository.findById(user.get().getId());
        Boolean followedToArticleOwner = userRepository.isFollowedToArticleOwner(user.get().getId(), article.get().getAuthor().getId());
        return followedToArticleOwner ? ResponseEntity.ok(userMapper.toProfileDto(user.get())) : null;

    }
}
