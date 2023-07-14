package io.realworld.angular.conduit.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Builder
@Data
public class ErrorDTO {
    private List<String> error;
}
