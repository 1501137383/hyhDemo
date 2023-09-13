package com.loop.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loop.demo.entity.Menu;
import com.loop.demo.vo.PermissionInfo;

import java.util.List;

public interface MenuRepository extends BaseMapper<Menu> {


    List<PermissionInfo> selectAuthorityMenuByUserId(Integer userId);
}