package com.loop.demo.service.impl;

import com.loop.demo.service.ElementService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loop.demo.entity.Element;
import com.loop.demo.mapper.ElementRepository;

@Service
public class ElementServiceImpl extends ServiceImpl<ElementRepository, Element> implements ElementService {

}


