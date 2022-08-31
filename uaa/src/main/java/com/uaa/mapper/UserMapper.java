package com.uaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.uaa.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
