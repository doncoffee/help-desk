package com.innowise.helpdesk.mapper;

public interface Mapper<T, R> {
    T mapToEntity(R object);
    R mapToDto(T object);
}
