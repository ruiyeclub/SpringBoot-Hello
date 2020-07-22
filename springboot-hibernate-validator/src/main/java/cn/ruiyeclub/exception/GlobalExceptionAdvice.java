package cn.ruiyeclub.exception;

import cn.ruiyeclub.entity.JsonData;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 全局异常处理类
 * @author: Ray。
 * @create: 2019-12-26 11:01
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {


    /**
     * 指定拦截异常的类型
     * @param e
     * @return json格式类型
     */
    @ExceptionHandler({Exception.class})
    public JsonData customExceptionHandler(Exception e) {
        //验证get请求的参数合法性
        if(e instanceof BindException){
            BindException bindException = (BindException) e;
            String msg =  bindException.getBindingResult().getFieldError().getDefaultMessage();
            return JsonData.buildError(msg);
        }

        //验证post请求的参数合法性
        if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException notValidException=(MethodArgumentNotValidException) e;
            String msg = notValidException.getBindingResult().getFieldError().getDefaultMessage();
            return JsonData.buildError(msg);
        }
        e.printStackTrace();
        return JsonData.buildError("系统异常",-2);
    }
}