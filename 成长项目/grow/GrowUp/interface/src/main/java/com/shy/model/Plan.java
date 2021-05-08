package com.shy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan implements Serializable {
    private Integer id;
    private String planDate;
    private String dayTime;
    private String planName;
    private String planText;
    private String planStatus;
}