package io.realworld.angular.conduit.customexseption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenValidationException  extends RuntimeException{
    private String message;
}
