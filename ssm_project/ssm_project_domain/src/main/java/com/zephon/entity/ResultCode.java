package com.zephon.entity;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.entity
 * 返回状态码
 */
public enum  ResultCode {
    /**
     * 成功
     */
    SUCCESS(true,10000,"操作成功！"),
    /**
     * 失败
     */
    FAIL(false,10001,"操作失败"),
    /**
     * 未认证
     */
    UNAUTHENTICATED(false, 10002, "您还未登录"),
    /**
     * 权限不足
     */
    UNAUTHORISE(false, 10003, "权限不足"),
    /**
     * 系统繁忙
     */
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),

    /**
     * 用户操作返回码：20001
     */
    USERNAME_PASSWORD_ERROR(false,20001,"用户名或密码错误"),

    INFORMATION_NOT_STANDARD(false,20002,"注册信息不规范"),

    /**
     * 产品操作返回码：30001
     */
    DATE_FORMAT_ERROR(false,30001,"日期格式错误"),

    /**
     * 角色操作返回码：40001
     */
    ROLE_INFORMATION_ERROR(false,40001,"角色信息错误"),

    /**
     * 权限操作返回码：50001
     */
    PERMISSION_INFORMATION_ERROR(false,50001,"权限信息错误");

    boolean success;
    Integer code;
    String message;
    ResultCode(boolean success,Integer code,String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    public boolean success(){
        return success;
    }
    public int code(){
        return code;
    }
    public String message(){
        return message;
    }
}