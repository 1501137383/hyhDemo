package com.loop.demo.service;

import com.loop.demo.vo.PermissionInfo;

import java.util.List;

public interface PermissionService {

    List<PermissionInfo> getPermissionByUserId(String userId);
}
