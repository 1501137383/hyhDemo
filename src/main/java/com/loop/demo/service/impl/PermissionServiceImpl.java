package com.loop.demo.service.impl;

import com.loop.demo.mapper.MenuRepository;
import com.loop.demo.service.PermissionService;
import com.loop.demo.vo.PermissionInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final MenuRepository menuRepository;

    @Override
    public List<PermissionInfo> getPermissionByUserId(String userId) {
        return menuRepository.selectAuthorityMenuByUserId(Integer.valueOf(userId));
    }
}
