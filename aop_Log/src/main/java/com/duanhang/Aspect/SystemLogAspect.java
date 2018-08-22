package com.duanhang.Aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.duanhang.Util.JsonUtil;
import com.duanhang.annotation.Log;
import com.duanhang.entity.SystemLog;
import com.duanhang.entity.User;
import com.duanhang.service.SystemLogService;


/**
 * 
 * @author duanhang
 *
 */

@Aspect
@Component
public class SystemLogAspect {

    //ע��Service���ڰ���־�������ݿ�  
//    @Resource  //��������resourceע�⣬һ���õ���@Autowired
//    private SystemLogService systemLogService;  
    
    private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);  
    
    //Controller���е�  
    @Pointcut("execution (* com.duanhang.controller.*.*(..))")  
    public  void controllerAspect() {  
    }  
    
    /** 
     * ǰ��֪ͨ ��������Controller���¼�û��Ĳ��� 
     * 
     * @param joinPoint �е� 
     */ 
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
    	System.out.println();
    	System.out.println();
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");
    	System.out.println("//**********������aop����־��¼***********//");

        System.out.println("==========ִ��controllerǰ��֪ͨ===============");
        if(logger.isInfoEnabled()){
            logger.info("before " + joinPoint);
        }
    }    
    
    //����controller����֪ͨ,ʹ���ڷ���aspect()��ע��������
      @Around("controllerAspect()")
      public void around(ProceedingJoinPoint joinPoint){
          System.out.println("==========��ʼִ��controller����֪ͨ===============");
          long start = System.currentTimeMillis();
          try {
              joinPoint.proceed();
              long end = System.currentTimeMillis();
              if(logger.isInfoEnabled()){
                  logger.info("around " + joinPoint + "\t����ʱ�� : " + (end - start) + " ms!");
              }
              System.out.println("==========����ִ��controller����֪ͨ===============");
          } catch (Throwable e) {
              long end = System.currentTimeMillis();
              if(logger.isInfoEnabled()){
                  logger.info("around " + joinPoint + "\t����ʱ�� : " + (end - start) + " ms with exception : " + e.getMessage());
              }
          }
      }
    
    /** 
     * ����֪ͨ ��������Controller���¼�û��Ĳ��� 
     * 
     * @param joinPoint �е� 
     */  
    @After("controllerAspect()")  
    public  void after(JoinPoint joinPoint) {  
  
       /* HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
        HttpSession session = request.getSession();  */
        //��ȡsession�е��û�  
       // User user = (User) session.getAttribute("user");  
        //�����IP  
        //String ip = request.getRemoteAddr();
        User user = new User();
        user.setId(1);
        user.setUsername("����");
        String ip = "127.0.0.1";
         try {  
            
            String targetName = joinPoint.getTarget().getClass().getName();  
            String methodName = joinPoint.getSignature().getName();  
            Object[] arguments = joinPoint.getArgs();  
            Class targetClass = Class.forName(targetName);  
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";
             for (Method method : methods) {  
                 if (method.getName().equals(methodName)) {  
                    Class[] clazzs = method.getParameterTypes();  
                     if (clazzs.length == arguments.length) {  
                         operationType = method.getAnnotation(Log.class).operationType();
                         operationName = method.getAnnotation(Log.class).operationName();
                         break;  
                    }  
                }  
            }
            //*========����̨���=========*//  
            System.out.println("=====controller����֪ͨ��ʼ=====");  
            System.out.println("���󷽷�:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);  
            System.out.println("��������:" + operationName);  
            System.out.println("������:" + user.getUsername());  
            System.out.println("����IP:" + ip);  
//            //*========���ݿ���־=========*//  
//            SystemLog log = new SystemLog();  
//            log.setId(UUID.randomUUID().toString());
//            log.setDescription(operationName);  
//            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);  
//            log.setLogType((long)0);  
//            log.setRequestIp(ip);  
//            log.setExceptioncode( null);  
//            log.setExceptionDetail( null);  
//            log.setParams( null);  
//            log.setCreateBy(user.getName());  
//            log.setCreateDate(new Date());  
//            //�������ݿ�  
////            systemLogService.insert(log);  
            System.out.println("=====controller����֪ͨ����=====");  
        }  catch (Exception e) {  
            //��¼�����쳣��־  
            logger.error("==����֪ͨ�쳣==");  
            logger.error("�쳣��Ϣ:{}", e.getMessage());  
        }  
    } 
    
    //���ú��÷���֪ͨ,ʹ���ڷ���aspect()��ע��������
      @AfterReturning("controllerAspect()")
      public void afterReturn(JoinPoint joinPoint){
          System.out.println("=====ִ��controller���÷���֪ͨ=====");  
              if(logger.isInfoEnabled()){
                  logger.info("afterReturn " + joinPoint);
              }
      }
    
    /** 
     * �쳣֪ͨ �������ؼ�¼�쳣��־ 
     * 
     * @param joinPoint 
     * @param e 
     */  
     @AfterThrowing(pointcut = "controllerAspect()", throwing="e")  
     public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {  
        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
        HttpSession session = request.getSession();  
        //��ȡsession�е��û�  
        User user = (User) session.getAttribute(WebConstants.CURRENT_USER);  
        //��ȡ����ip  
        String ip = request.getRemoteAddr(); */ 
        //��ȡ�û����󷽷��Ĳ��������л�ΪJSON��ʽ�ַ���  
        
        User user = new User();
        user.setId(1);
        user.setUsername("����");
        String ip = "127.0.0.1";
        
        String params = "";  
         if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
//             for ( int i = 0; i < joinPoint.getArgs().length; i++) {  
//                params += JsonUtil.getJsonStr(joinPoint.getArgs()[i]) + ";";  
//            }  
//        params =  JsonUtil.listObject2ListJson(Arrays.asList(joinPoint.getArgs()));
        	params =  JsonUtil.object2Json(joinPoint.getArgs());
        }  
         try {  
             
             String targetName = joinPoint.getTarget().getClass().getName();  
             String methodName = joinPoint.getSignature().getName();  
             Object[] arguments = joinPoint.getArgs();  
             Class targetClass = Class.forName(targetName);  
             Method[] methods = targetClass.getMethods();
             String operationType = "";
             String operationName = "";
              for (Method method : methods) {  
                  if (method.getName().equals(methodName)) {  
                     Class[] clazzs = method.getParameterTypes();  
                      if (clazzs.length == arguments.length) {  
                          operationType = method.getAnnotation(Log.class).operationType();
                          operationName = method.getAnnotation(Log.class).operationName();
                          break;  
                     }  
                 }  
             }
             /*========����̨���=========*/  
            System.out.println("=====�쳣֪ͨ��ʼ=====");  
            System.out.println("�쳣����:" + e.getClass().getName());  
            System.out.println("�쳣��Ϣ:" + e.getMessage());  
            System.out.println("�쳣����:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);  
            System.out.println("��������:" + operationName);  
            System.out.println("������:" + user.getUsername());  
            System.out.println("����IP:" + ip);  
            System.out.println("�������:" + params);  
//               /*==========���ݿ���־=========*/  
//            SystemLog log = new SystemLog();
//            log.setId(UUID.randomUUID().toString());
//            log.setDescription(operationName);  
//            log.setExceptioncode(e.getClass().getName());  
//            log.setLogType((long)1);  
//            log.setExceptionDetail(e.getMessage());  
//            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
//            log.setParams(params);  
//            log.setCreateBy(user.getName());  
//            log.setCreateDate(new Date());  
//            log.setRequestIp(ip);  
//            //�������ݿ�  
////            systemLogService.insert(log);  
            System.out.println("=====�쳣֪ͨ����=====");  
        }  catch (Exception ex) {  
            //��¼�����쳣��־  
            logger.error("==�쳣֪ͨ�쳣==");  
            logger.error("�쳣��Ϣ:{}", ex.getMessage());  
        }  
         /*==========��¼�����쳣��־==========*/  
        logger.error("�쳣����:{}�쳣����:{}�쳣��Ϣ:{}����:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);  
  
    }  
    
}