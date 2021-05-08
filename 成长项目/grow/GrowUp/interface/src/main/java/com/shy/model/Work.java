package com.shy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Work implements Serializable {
    private Integer id;
    private String workTime;
    private String workTask;
    private String workName;
}