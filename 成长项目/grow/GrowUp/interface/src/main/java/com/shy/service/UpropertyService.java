package com.shy.service;

import com.shy.model.Property;
import com.shy.model.Uproperty;

public interface UpropertyService {
    int insert(Uproperty uproperty);
    int delete(Integer id);
}
