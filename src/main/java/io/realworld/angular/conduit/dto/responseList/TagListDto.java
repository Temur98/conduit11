package io.realworld.angular.conduit.dto.responseList;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagListDto {
    List<String> tags;
}
