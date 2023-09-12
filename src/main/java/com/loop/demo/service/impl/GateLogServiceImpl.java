package com.loop.demo.service.impl;

import com.loop.demo.service.GateLogService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loop.demo.mapper.GateLogRepository;
import com.loop.demo.entity.GateLog;

@Service
public class GateLogServiceImpl extends ServiceImpl<GateLogRepository, GateLog> implements GateLogService {

}


