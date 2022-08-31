package com.uaa.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uaa.entity.PermissionEntry;
import com.uaa.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService extends ServiceImpl<PermissionMapper, PermissionEntry> {


    public List<PermissionEntry> getPermissionsByUserId(Integer userId){

        return baseMapper.selectPermissionsByUserId(userId);
    }

}
