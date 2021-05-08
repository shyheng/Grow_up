package com.shy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Target implements Serializable {
    private Integer id;
    private String tarContent;
    private String tarStart;
    private String tarOver;
    private String tarName;
}