package com.loop.demo.service.impl;

import com.loop.demo.service.GroupLeaderService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loop.demo.entity.GroupLeader;
import com.loop.demo.mapper.GroupLeaderRepository;

@Service
public class GroupLeaderServiceImpl extends ServiceImpl<GroupLeaderRepository, GroupLeader> implements GroupLeaderService {

}


