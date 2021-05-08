package com.shy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDiary implements Serializable {
    private Integer id;
    private String diaryMood;
    private String diaryDate;
    private String diaryName;
    private String diaryText;
    private String diaryFile;
}