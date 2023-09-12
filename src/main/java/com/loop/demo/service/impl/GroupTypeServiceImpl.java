package com.loop.demo.service.impl;

import com.loop.demo.service.GroupTypeService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loop.demo.mapper.GroupTypeRepository;
import com.loop.demo.entity.GroupType;

@Service
public class GroupTypeServiceImpl extends ServiceImpl<GroupTypeRepository, GroupType> implements GroupTypeService {

}


