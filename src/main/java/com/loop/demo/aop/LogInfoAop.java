package com.loop.demo.aop;

import com.alibaba.fastjson.JSONObject;
import com.loop.demo.annotation.LogInfo;
import com.loop.demo.context.BaseContextHandler;
import com.loop.demo.entity.GateLog;
import com.loop.demo.mapper.GateLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class LogInfoAop {


    private final GateLogRepository gateLogRepository;

    /**
     * 指定切面
     */
    @Pointcut(value = "@annotation(com.loop.demo.annotation.LogInfo)")
    public void webLog() {
    }

    /**
     * 前置通知, 方法调用前调用
     */
    @Before(value = "@annotation(logInfo)")
    public void doBeforeLog(JoinPoint joinPoint, LogInfo logInfo) {
        log.info("前置通知, 方法调用前调用");

    }

    /**
     * 获取请求参数的Json格式显示
     *
     * @param filters 过滤器，过滤不需要转换json的类型
     * @return 所有参数的json显示
     */
    private JSONObject getJsonFmtDisplayOfReqParam(JoinPoint joinPoint, Class<?>... filters) {
        JSONObject re = new JSONObject();
        //参数值
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return re;
        }
        //参数名称
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        argIter:
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            for (Class<?> filter : filters) {
                if (arg == null || filter.isAssignableFrom(arg.getClass())) continue argIter;
            }
            if (parameterNames != null) re.put(parameterNames[i], arg);
        }
        return re;
    }

    /**
     * 后置通知, 方法调用后调用
     */
    @After(value = "webLog()")
    public void afterLog(JoinPoint jp) {
        log.info("后置通知, 方法调用后调用");
    }

    /**
     * 方法执行成功调用
     */
    @AfterReturning(value = "@annotation(logInfo)")
    public void doAfterReturning(JoinPoint joinPoint, LogInfo logInfo) throws Throwable {
        log.info("方法执行成功插入日志");
        //插入成功日志
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("方法名name: = " + name);
        String declaringTypeName = signature.getDeclaringTypeName();
        System.out.println("方法所在包: = " + declaringTypeName);
        //转成方法
        MethodSignature methodSignature= (MethodSignature)signature;
        String[] parameterNames = methodSignature.getParameterNames();
        System.out.println("参数名："+ Arrays.toString(parameterNames));
        System.out.println("参数值:" + Arrays.toString(joinPoint.getArgs()));
        // 接收到请求，记录请求内容
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 记录下请求内容
        System.out.println("请求URL : " + req.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + req.getMethod());
        System.out.println("IP : " + req.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //构造请求参数json
        JSONObject jsonParam = getJsonFmtDisplayOfReqParam(joinPoint, logInfo.parameterFilters());

        GateLog gateLog=new GateLog();
        gateLog.setOpt(req.getMethod());
        gateLog.setUri(req.getRequestURI());
        gateLog.setMenu(signature.getName());
        gateLog.setCrtTime(new Date());
        gateLog.setCrtUser(BaseContextHandler.getUserId());
        gateLog.setCrtName(BaseContextHandler.getName());
        gateLog.setRequest(jsonParam.toJSONString());
        gateLogRepository.insert(gateLog);


    }

    /**
     * 后置异常通知
     */
    @AfterThrowing(value = "@annotation(logInfo)")
    public void throwss(JoinPoint joinPoint, LogInfo logInfo){
        log.info("方法执行失败插入日志");
        //插入异常日志

    }




    /**
     * 环绕通知,环绕增强，相当于MethodInterceptor
     * @param pjp
     * @return
     */
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        try {
            Object o =  pjp.proceed();
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }






}
