package com.lexiaoyao.aopdemo.aop;

import com.lexiaoyao.aopdemo.Ann;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AppAop {

    @Pointcut("execution(public * com.lexiaoyao.aopdemo.service.AppService.function1(..)))")
    public void BrokerAspect() {

    }

    @Pointcut("@annotation(com.lexiaoyao.aopdemo.Ann)")
    public void otherAspect() {

    }

    /**
     * 环绕增强
     *
     * @param pjp
     * @throws Throwable
     */
    @Around("otherAspect()")
    public void doAroundGameData(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object[] args = pjp.getArgs();//获取参数
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            String name = signature.getMethod().getName();//获取方法名
            Ann declaredAnnotation = getDeclaredAnnotation(pjp);
            System.out.println(declaredAnnotation.value());
            System.out.println(name);//前置增强
            Object proceed = pjp.proceed();//获取返回值
            System.out.println(proceed);//后置增强
        } catch (Throwable e) {
            //在切面里可以捕获切点抛出的异常
            System.out.println("异常通知");
        }
    }

    public Ann getDeclaredAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        Ann annotation = objMethod.getDeclaredAnnotation(Ann.class);
        // 返回
        return annotation;
    }

    /**
     * @description 在连接点执行之前执行的通知
     */
    @Before("BrokerAspect()")
    public void doBeforeGame(JoinPoint joinPoint) {
        Class<?> aClass1 = joinPoint.getTarget().getClass();
        System.out.println(aClass1);
        String methodName = joinPoint.getSignature().getName();
        Class<?> aClass = joinPoint.getTarget().getClass();
        System.out.println(aClass.getName());
        System.out.println(methodName);
        System.out.println("前置增强");
    }

    /**
     * @description 在连接点执行之后执行的通知（返回通知和异常通知的异常）
     */
    @After("BrokerAspect()")
    public void doAfterGame(JoinPoint joinPoint) {
        System.out.println("后置增强");
    }


    /**
     * @description 在连接点执行之后执行的通知（异常通知）
     */
    @AfterThrowing("BrokerAspect()")
    public void doAfterThrowingGame(JoinPoint joinPoint) {
        System.out.println("捕获异常");
    }

    /**
     * 后置增强
     *
     * @param jp
     * @param returnValue
     * @throws Throwable
     */
    @AfterReturning(value = "BrokerAspect()", returning = "returnValue")
    public void logMethodCall(JoinPoint jp, Object returnValue) throws Throwable {
        System.out.println("进入后置增强了！");

        String name = jp.getSignature().getName();
        System.out.println(name);
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            System.out.println("参数：" + arg);
        }
        System.out.println("方法返回值为：" + returnValue);
    }


}
