package io.realworld.angular.conduit.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto <T>{
    /**
     * Response code that represents status of response.
     * -2 - VALIDATION ERROR
     * -1 - NOT FOUND
     * 0 - OK
     * 1 - DATABASE ERROR
     */
    private Integer code;
    private String message;
    private Boolean success;
    private T data;
}
