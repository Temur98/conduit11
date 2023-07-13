package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.response.CommonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagService {

    ResponseEntity<CommonResponse<List<String>>> getAll();
}
