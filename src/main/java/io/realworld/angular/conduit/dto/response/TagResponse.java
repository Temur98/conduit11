package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagResponse {
    private List<TagDTO> tags;
}
