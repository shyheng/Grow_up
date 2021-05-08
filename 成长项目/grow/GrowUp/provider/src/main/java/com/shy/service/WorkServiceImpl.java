package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.WorkMapper;
import com.shy.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = WorkService.class,version = "1.0.0",timeout = 15000)
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkMapper workMapper;

    @Override
    public List<Work> select(String name) {
        return workMapper.selectByPrimaryKey(name);
    }

    @Override
    public int insert(Work work) {
        return workMapper.insert(work);
    }

    @Override
    public List<Work> selectTime(String workTime) {
        return workMapper.selectTime(workTime);
    }

    @Override
    public int upData(Work work) {
        return workMapper.updateByPrimaryKey(work);
    }

    @Override
    public int delete(Integer id) {
        return workMapper.deleteByPrimaryKey(id);
    }
}
