package com.homework.sat02.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.homework.sat02.annotation.ReadOnly;
import com.homework.sat02.entity.User;
import com.homework.sat02.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestService {
    @Autowired
    private UserMapper userMapper;

    @ReadOnly("master")
    public void getUserFromMaster() {
        User user = new User();
        Wrapper wrapper = new QueryWrapper<>(user);
        List list = userMapper.selectList(wrapper);
        log.info("getUserFromMaster:{}", list);
    }

    @ReadOnly("slave")
    public void getUserFromSlave() {
        User user = new User();
        Wrapper wrapper = new QueryWrapper<>(user);
        List list = userMapper.selectList(wrapper);
        log.info("getUserFromSlave:{}", list);
    }
}
