package com.loop.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loop.demo.entity.Element;

import java.util.List;

public interface ElementRepository extends BaseMapper<Element> {

    List<Element> getAllElementPermissions();
}