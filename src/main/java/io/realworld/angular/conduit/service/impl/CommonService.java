package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.exception.WrongSlugException;

public class CommonService {
    public static Long getIdBySlug(String slug) {
        try {
            System.out.println("----> " + slug);
            return Long.parseLong(slug.split("-")[slug.split("-").length - 1]);
        } catch (NumberFormatException e){
            e.printStackTrace();
            throw new WrongSlugException("Slug is not correct");
        }
    }
}
