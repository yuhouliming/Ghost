package com.example.dockerdemo.config;

import com.example.dockerdemo.domain.Result;
import com.example.dockerdemo.domain.ResultCode;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author: lmwu
 * @Date: 2021/1/20 16:21
 * @Description: 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory
            .getLogger(GlobalExceptionHandler.class);

    /**
     * 拦截捕捉自定义异常 BusinessErrorException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result AIExceptionHandler(Exception ex) {
        logger.error("操作失败报错{}", ex);
        if (ex.getCause() != null && ex.getCause().toString().contains("SQLIntegrityConstraintViolationException")) {
            return new Result(ResultCode.FAIL.code(), "添加失败,字段重复,请仔细检查重复字段", false);
        }
        if (ex instanceof UnknownAccountException) {
            return new Result(ResultCode.USERNAME_IS_NULL);
        }else if (ex instanceof LockedAccountException) {
            return new Result(ResultCode.USERNAME_IS_DISABLED);
        }else if (ex instanceof AuthenticationException){
            return new Result(ResultCode.USERNAME_OR_PASSWORDIS_ERROR);
        }
        return new Result(ResultCode.FAIL, ex.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public Result UserExceptionHandler(Exception ex) {
        logger.error("操作失败报错{}", ex);
        return new Result(ResultCode.FAIL, "用户名或密码错误");
    }

//    /**
//     * 处理空指针的异常
//     * @param req
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value =BusinessErrorException.class)
//    @ResponseBody
//    public Result exceptionHandler(HttpServletRequest req, NullPointerException e){
//        logger.error("发生空指针异常！原因是:",e);
//        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
//    }


}

