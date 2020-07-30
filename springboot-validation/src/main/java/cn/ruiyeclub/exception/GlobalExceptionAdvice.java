package cn.ruiyeclub.exception;

import cn.ruiyeclub.entity.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ray。
 * @date 2020/7/30 18:22
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    private static final String BAD_REQUEST_MSG = "客户端请求参数错误";

    /**
     * 处理form-data方式调用接口校验失败抛出的异常
     */
    @ExceptionHandler(BindException.class)
    public JsonData bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getDefaultMessage())
                .collect(Collectors.toList());
        log.info("form-data方式{}",collect.toString());
        return JsonData.buildError(BAD_REQUEST_MSG, HttpStatus.BAD_REQUEST.value());
    }

    /**
     * 处理json请求体调用接口校验失败抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonData methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getDefaultMessage())
                .collect(Collectors.toList());
        log.info("json请求体{}",collect.toString());
        return JsonData.buildError(BAD_REQUEST_MSG,HttpStatus.BAD_REQUEST.value());
    }

    /**
     * 处理单个参数校验失败抛出的异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonData constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(o -> o.getMessage())
                .collect(Collectors.toList());
        log.info("单个参数校验{}",collect.toString());
        return JsonData.buildError(BAD_REQUEST_MSG, HttpStatus.BAD_REQUEST.value());
    }

}
