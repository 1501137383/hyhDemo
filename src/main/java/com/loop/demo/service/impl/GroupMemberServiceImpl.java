package com.loop.demo.service.impl;

import com.loop.demo.service.GroupMemberService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loop.demo.entity.GroupMember;
import com.loop.demo.mapper.GroupMemberRepository;

@Service
public class GroupMemberServiceImpl extends ServiceImpl<GroupMemberRepository, GroupMember> implements GroupMemberService {

}


