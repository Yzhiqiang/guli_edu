package com.yu.edu.exceptionhandler;

import com.yu.commonUtils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 15:18 2021/10/31
 * @Modified By:
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 特殊异常处理
     * @param arithmeticException
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException arithmeticException)
    {
        arithmeticException.printStackTrace();
        return R.error().message("执行了ArithmeticException处理");
    }

    /**
     * 自定义异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e)
    {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

    /**
     * 指定出现什么异常执行该方法
     * @param excetption
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody    //返回数据
    public R error(Exception excetption)
    {
        excetption.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }
}
