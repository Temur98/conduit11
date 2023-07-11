package io.realworld.angular.conduit.service;

public class CommonService {
        public static Long getIdBySlug(String slug) {
            return Long.valueOf(slug.substring(slug.lastIndexOf("-") + 1));
        }

}
