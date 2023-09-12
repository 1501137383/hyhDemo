package com.loop.demo.service.impl;

import com.loop.demo.service.GroupService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loop.demo.mapper.GroupRepository;
import com.loop.demo.entity.Group;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupRepository, Group> implements GroupService {

}


