package com.homework.sat03.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homework.sat03.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
