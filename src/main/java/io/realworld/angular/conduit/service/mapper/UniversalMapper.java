package io.realworld.angular.conduit.service.mapper;

public interface UniversalMapper <D, E>{
    D toDto(E entity);
    E toEntity(D dto);
}
