package com.loop.demo.service.impl;

import com.loop.demo.service.MenuService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loop.demo.entity.Menu;
import com.loop.demo.mapper.MenuRepository;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuRepository, Menu> implements MenuService {

}


