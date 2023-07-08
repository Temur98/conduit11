package io.realworld.angular.conduit.service.mapper;

import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper extends UniversalMapper<ArticleDto, Article>{
//    @Mapping(target = "tags", expression = "java(dto.getTags().stream().map(tagDto -> tagDto.getName()).toList())")
//    Article toEntity(ArticleDto dto);
//    @Mapping(target = "tags", expression = "java(entity.getTags().stream().map(tag -> tag.getName()).toList())")
//    ArticleDto toDto(Article entity);
}
