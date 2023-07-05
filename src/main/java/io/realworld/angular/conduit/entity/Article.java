package io.realworld.angular.conduit.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Article
{
    @Id
    @GeneratedValue
    private Long id;
}
