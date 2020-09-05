package com.zty.ssm.controller;

import com.zty.ssm.domain.SysLog;
import com.zty.ssm.service.ISysLogService;
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
import javax.sql.rowset.Joinable;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;//需要web配置

    @Autowired
    private ISysLogService iSysLogService;
    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法
    //前置通知  主要是获取开始时间 执行的类 执行的方法
    //拦截controler下的所有类的所有方法
    @Before("execution(* com.zty.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        startTime=new Date();  //当前的时间就是操作时间
        executionClass=jp.getTarget().getClass();  //操作的类
        String executionMethodName=jp.getSignature().getName();//获取访问的方法名称
        Object[] args = jp.getArgs();//获取访问的方法的参数

        //获取具体执行的method对象
        if (args==null||args.length==0){
            //无参数
            executionMethod=executionClass.getMethod(executionMethodName);//只能获取无参数的方法
        }else {
            //有参数
            Class[] classes=new Class[args.length];
            for(int i=0;i<args.length;i++){
                classes[i]=args[i].getClass();
            }
            executionClass.getMethod(executionMethodName,classes);
        }
    }

    //前置通知
    //拦截controler下的所有类的所有方法
    @After("execution(* com.zty.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time =new Date().getTime()-startTime.getTime();//获取访问时长

        //获取url
        String url="";
        if (executionClass!=null&&executionMethod!=null&&executionClass!=LogAop.class){//前置操作获取的类和方法不为空
            //获取类上的@RequestMapping
            RequestMapping requestMapping=(RequestMapping)executionClass.getAnnotation(RequestMapping.class);
            if (requestMapping!=null){
                String[] classvalue = requestMapping.value();
                //获取方法上的
                RequestMapping method= executionMethod.getAnnotation(RequestMapping.class);
                if (method!=null){
                    String[] MethodValue = method.value();
                    url=classvalue[0]+MethodValue[0];
                }
            }
        }

        //获取访问ip地址
        String ip=request.getRemoteAddr();
        //获取当前用户
        //通过spring
        SecurityContext context= SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
        User user = (User)context.getAuthentication().getPrincipal();
        String username = user.getUsername();
        // request.getSession().getAttribute("SPRING_SECURITY_CONTEXT") 通过request进行获取


        //将相关信息封装到SysLog对象中
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);//执行时长
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]"+executionClass.getName()+"[方法名]"+executionMethod.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(startTime);//开始时间


        //调用Service完成操作
        iSysLogService.save(sysLog);
    }
}
