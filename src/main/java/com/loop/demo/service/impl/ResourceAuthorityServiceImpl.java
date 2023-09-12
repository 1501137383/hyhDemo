package com.loop.demo.service.impl;

import com.loop.demo.service.ResourceAuthorityService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loop.demo.entity.ResourceAuthority;
import com.loop.demo.mapper.ResourceAuthorityRepository;

@Service
public class ResourceAuthorityServiceImpl extends ServiceImpl<ResourceAuthorityRepository, ResourceAuthority> implements ResourceAuthorityService {

}


