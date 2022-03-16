package com.frontrow.springapp.mapper;

public interface AbstractMapper<E, V> {
    void entityToView(E e, V v);
    void viewToEntity(E e, V v);
}
