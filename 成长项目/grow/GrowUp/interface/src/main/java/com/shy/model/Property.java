package com.shy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property implements Serializable {
    private Integer id;
    private String propertyDate;
    private String propertyType;
    private String incomeExpenditure;
    private String propertyMoney;
    private String propertyRemarks;
}