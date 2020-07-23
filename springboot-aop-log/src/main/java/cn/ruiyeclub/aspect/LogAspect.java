package cn.ruiyeclub.aspect;

import cn.ruiyeclub.annotation.Log;
import cn.ruiyeclub.entity.SysLog;
import cn.ruiyeclub.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * 使用aop将@Log注解定义为切点，然后实现切面类LogAspect，在切面类实现日志在数据库的存储
 * @author Ray。
 * @create 2020-06-07 22:11
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 表示匹配带有自定义注解的方法
     */
    @Pointcut("@annotation(cn.ruiyeclub.annotation.Log)")
    public void pointcut(){}

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point){
        Object result=null;
        long beginTime=System.currentTimeMillis();

        try {
            log.info("我在目标方法之前执行！");
            result=point.proceed();
            long entTime=System.currentTimeMillis();
            insertLog(point,entTime-beginTime);
        }catch (Throwable e){
            log.info("异常...{}",e);
        }
        return result;
    }

    private void insertLog(ProceedingJoinPoint point,long time){
        MethodSignature signature=(MethodSignature)point.getSignature();
        Method method=signature.getMethod();
        SysLog sysLog=new SysLog();

        //获取注解上面的描述
        Log userAction=method.getAnnotation(Log.class);
        if(userAction!=null){
            sysLog.setUserAction(userAction.value());
        }

        //请求的类名
        String className=point.getTarget().getClass().getName();
        //请求的方法名
        String methodName=signature.getName();
        //请求的方法参数值
        String args= Arrays.toString(point.getArgs());

        //TODO 从token中获取用户信息
        sysLog.setUserId(11);
        sysLog.setCreateTime(new Date());

        log.info("类名：{}，方法名：{}，参数：{}，执行时间：{}",className,methodName,args,time);
        sysLogService.insert(sysLog);

    }

}