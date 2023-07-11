package io.realworld.angular.conduit.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDTO {
    private String error;
}
