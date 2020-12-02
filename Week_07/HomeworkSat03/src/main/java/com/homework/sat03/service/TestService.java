package com.homework.sat03.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.homework.sat03.entity.User;
import com.homework.sat03.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class TestService {
    @Resource
    private UserMapper userMapper;

    public void getUserFromMaster() {
        User user = new User();
        HintManager hintManager = HintManager.getInstance();
//        hintManager.setDatabaseShardingValue("ds0");
        hintManager.setMasterRouteOnly();
        Wrapper wrapper = new QueryWrapper<>(user);
        List list = userMapper.selectList(wrapper);
        hintManager.close();
        log.info("getUserFromMaster:{}", list);
    }

    public void getUserFromSlave() {
        User user = new User();
        Wrapper wrapper = new QueryWrapper<>(user);
        List list = userMapper.selectList(wrapper);
        log.info("getUserFromSlave:{}", list);
    }

    public void insertUser() {
        User user = new User();
        user.setUserName("wang");
        user.setNickName("master_wang");
        userMapper.insert(user);
    }

    public void deleteUser() {
        User user = new User();
        user.setUserName("wang");
        user.setNickName("master_wang");
        Wrapper wrapper = new QueryWrapper<>(user);
        userMapper.delete(wrapper);
    }
}
