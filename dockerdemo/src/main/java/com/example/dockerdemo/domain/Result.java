package com.example.dockerdemo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据响应对象
 *    {
 *      success ：是否成功
 *      code    ：返回码
 *      message ：返回信息
 *      //返回数据
 *      data：  ：{
 *
 *      }
 *    }
 */

@ApiModel(value = "返回实体")
public class Result {
    @ApiModelProperty(value = "是否成功")
    private boolean success;//是否成功
    @ApiModelProperty(value = "返回码")
    private Integer code;//返回码
    @ApiModelProperty(value = "返回信息")
    private String message;//返回信息
    @ApiModelProperty(value = "返回对象")
    private Object data;//返回数据


    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public Result(){

    }

    public Result(ResultCode code, Object data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public Result(Integer code, Object data, String userid, boolean success, String message) {
        this.success=success;
        this.code = code;
        this.data = data;
        this.message=message;
    }

    public Result(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }
    public Result(boolean success, Integer code, String message, Object data){
        this.success=success;
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result ERROR(){
        return new Result(ResultCode.SERVER_ERROR);
    }

    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }

    public void setSuccess(boolean success){
        this.success=success;
    }
    public boolean isSuccess(){
        return success;
    }
    public Object getData(){
        return data;
    }
    public void setData(Object data){
        this.data = data;
    }
    public Integer getCode(){
        return code;
    }
    public void setCode(Integer code){
        this.code=code;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
}
