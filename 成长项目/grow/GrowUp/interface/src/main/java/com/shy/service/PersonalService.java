package com.shy.service;

import com.shy.model.Personal;

import java.util.List;

public interface PersonalService {
    int insert(Personal personal);
    List<Personal> select(String username);
    List<Personal> selectId(String phone);
    int delete(String phone);
}
