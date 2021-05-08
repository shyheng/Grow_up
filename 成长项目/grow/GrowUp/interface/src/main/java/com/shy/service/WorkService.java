package com.shy.service;

import com.shy.model.Work;

import java.util.List;

public interface WorkService {
   List<Work> select(String name);
   int insert(Work work);
   List<Work> selectTime(String workTime);
   int upData(Work work);
   int delete(Integer id);
}
