package com.frontrow.springapp.mapper;

import com.frontrow.springapp.pojo.UserView;

public interface AbstractMapper<E, V> {
    void entityToView(E e, V v);
    void viewToEntity(E e, V v);
    UserView entityToView(E e);
}
