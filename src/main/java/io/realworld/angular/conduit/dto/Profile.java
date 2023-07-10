package io.realworld.angular.conduit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    private String username;
    private String bio;
    private String image;
    private Boolean following;
}
