package uz.najottalim.javan6.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String email;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(defaultValue = "")
    private String bio;
    @JsonProperty(value = "image",defaultValue = "image/path")
    private String imagePath;
    @JsonProperty(defaultValue = "false")
    private Boolean following;
}
