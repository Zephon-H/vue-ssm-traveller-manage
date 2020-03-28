package com.zephon.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.entity
 * 返回结果
 */
@Getter
@Setter
public class Result {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public Result(ResultCode resultCode) {
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.message = resultCode.message;
    }

    public Result(ResultCode resultCode, Object data) {
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.message = resultCode.message;
        this.data = data;
    }
}