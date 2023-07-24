package io.realworld.angular.conduit.dto.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Map;

public class CommentResponse<T> {
    private Map<String, T> properties;

    @JsonAnySetter
    public void add(String key, T value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, T> getProperties() {
        return properties;
    }
    public void clearProperties(){
        this.properties.clear();
    }
}
