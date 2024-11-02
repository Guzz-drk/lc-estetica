package br.com.dev.guzz.lcEstetica.utils;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BasicLogger {

    private static final Logger log = LoggerFactory.getLogger(BasicLogger.class);
    private static final Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();

    @Before("execution(* br.com.dev.guzz.lcEstetica.controller..*(..))")
    public void printRequest(JoinPoint jp){
        String controllerAndMethod = getUsedControllerAndMethod(jp);
        String json = gson.toJson(jp.getArgs());
        log.info("{} request: {}", controllerAndMethod, json);
    }

    @AfterReturning(pointcut = "execution(* br.com.dev.guzz.lcEstetica.controller..*(..))", returning = "result")
    public void printResponse(JoinPoint jp, Object result){
        String controllerAndMethod = getUsedControllerAndMethod(jp);
        String json = gson.toJson(result);
        log.info("{} response: {}", controllerAndMethod, json);
    }

    private String getUsedControllerAndMethod(JoinPoint jp){
        String controller = jp.getTarget().getClass().getSimpleName();
        String method = jp.getSignature().getName();
        return controller.concat("/").concat(method);
    }
}
