package com.example.dockerdemo.domain;

/**
 * 公共的返回码
 * 返回码code：
 * 成功：10000
 * 失败：10001
 * 未登录：10002
 * 未授权：10003
 * <p>
 * ...
 */
@SuppressWarnings(value = "all")

public enum ResultCode {

    SUCCESS(true, 10000, "操作成功！"),
    //---系统错误返回码-----
    FAIL(false,10001,"操作失败"),
    UNAUTHENTICATED(false,10002,"您还未登录"),
    UNAUTHORISE(false,10003,"权限不足"),
    INVALID_PARAM(false,10004,"非法参数！"),
    PARAM_DATA_NOT_EXIST(true,10005,"参数不存在!"),
    NEED_REQUIRED_PARAMS(false,10006,"参数填写不完整"),
    USERNAME_IS_NULL(false,10006,   "用户名不存在"),
    USERNAME_IS_DISABLED(false,10007,"当前账号已禁用"),
    USERNAME_OR_PASSWORDIS_NULL(false,10006,"用户名或密码不能为空"),
    USERNAME_OR_PASSWORDIS_ERROR(false,10007,"用户名或密码错误"),
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),
    ;

    //---用户操作返回码----
    //---企业操作返回码----
    //---权限操作返回码----
    //---其他操作返回码----
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}
