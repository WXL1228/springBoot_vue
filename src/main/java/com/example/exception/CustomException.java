package com.example.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends  RuntimeException {
    private  String code;
    private  String msg;

    public CustomException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public CustomException( String msg) {
        super(msg);
        this.code = "500";
        this.msg = msg;
    }
}
