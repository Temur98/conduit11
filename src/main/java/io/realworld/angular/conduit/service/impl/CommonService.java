package io.realworld.angular.conduit.service.impl;

public class CommonService {
    public static Long getIdBySlug(String slug) {
        Long id = Long.parseLong(slug.split("-")[slug.split("-").length-1]);
        System.out.println(id);
        return id;
    }
}
