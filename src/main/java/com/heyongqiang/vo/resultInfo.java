package com.heyongqiang.vo;


import lombok.Getter;
import lombok.Setter;

/**
 * 封装返回的结果的类
 *          状态码
 *              成功200 失败400
 *          提示信息
 *          返回的对象
 */
public class resultInfo<T> {
    private Integer Code;       //状态码
    private String Msg;        //提示信息
    private T Result;       //返回的对象(string javabean 集合 map)

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public T getResult() {
        return Result;
    }

    public void setResult(T result) {
        Result = result;
    }
}
