package io.realworld.angular.conduit.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("tag")
public record TagDTO(
        Long id,
        String name
) {
}
