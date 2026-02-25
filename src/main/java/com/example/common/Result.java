package com.example.common;

import lombok.Data;

@Data
public class Result {
    private static final String SUCCESS_CODE = "200";
    private static final String ERROR_CODE = "500";
    private String code;
    private String msg;
    private Object data;


    public  static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMsg("成功");
        return result;
    }
    public  static Result success(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        result.setMsg("成功");
        return result;
    }
    public  static Result success(String msg,Object data) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }
    public  static Result error(String msg) {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }
    public  static Result error(String msg, Object data) {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

}
