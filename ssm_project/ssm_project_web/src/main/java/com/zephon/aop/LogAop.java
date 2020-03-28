package com.zephon.aop;

import com.zephon.domain.SysLog;
import com.zephon.service.ILogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Zephon
 * @version V1.0
 */
@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ILogService logService;
    /**
     * 开始时间
     */
    private Date visitTime;
    /**
     * 访问的类
     */
    private Class cls;
    /**
     * 访问的方法
     */
    private Method method;

    /**
     * 前置通知
     * 用于获取开始时间，执行的类是哪一个，执行的方法是哪一个
     *
     * @param jp
     */
    @Before("execution(* com.zephon.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        cls = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        // 获取访问方法的参数
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
            method = cls.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            try {
                method = cls.getMethod(methodName, classArgs);
            } catch (NoSuchMethodException e) {
                method = null;
//                e.printStackTrace();
            } catch (SecurityException  e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 后置通知
     *
     * @param jp
     */
    @After("execution(* com.zephon.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        // 获取执行时长
        long time = System.currentTimeMillis() - visitTime.getTime();
        // 获取URL
        String url = "";
        if (cls != null && method != null && cls != LogAop.class) {
            url = request.getRequestURI();
            String ip = request.getRemoteAddr();
            String username= (String) request.getSession().getAttribute("username");

            if ("/ssm/logs".equals(url)) {
                return;
            }
            // 将日志相关信息封装到Syslog对象
            SysLog sysLog = new SysLog();
            sysLog.setExecutionTime(time);
            sysLog.setIp(ip);
            sysLog.setUrl(url);
            sysLog.setUsername(username);
            sysLog.setVisitTime(visitTime);
            sysLog.setMethod("[类名] " + cls.getName() + "[方法名] " + method.getName());
            // 调用 Service完成插入操作
            logService.save(sysLog);

        }

    }
}
