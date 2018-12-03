package com.fengwenyi.example.miaosha.exception;

import com.fengwenyi.example.miaosha.result.CodeMsg;
import com.fengwenyi.example.miaosha.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常处理
 * @author Wenyi Feng
 * @since 2018-11-29
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        if (e instanceof GlobalException) {
          GlobalException ex = (GlobalException) e;
          return Result.error(ex.getCm());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;

            List<ObjectError> errorList = ex.getAllErrors();
            ObjectError error = errorList.get(0);

            String msg = error.getDefaultMessage();

            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

}
