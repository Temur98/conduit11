package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.exception.SlugException;

public class CommonService {
    public static Long getIdBySlug(String slug) {
        String[] split = slug.split("-");
        System.out.println(split.length);
        if (split.length <= 1){
            throw new SlugException("Slug is not correct");
        }
        return Long.parseLong(split[split.length - 1]);
    }

}
