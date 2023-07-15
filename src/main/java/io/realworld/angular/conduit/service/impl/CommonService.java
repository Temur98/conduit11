package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.exception.WrongSlugException;

public class CommonService {
    public static Long getIdBySlug(String slug) {
        String[] split = slug.split("-");
        System.out.println(split.length);
        if (split.length <= 1){
            throw new WrongSlugException("Slug is not correct");
        }
        return Long.parseLong(split[split.length - 1]);
    }
}
