package com.em.parent.common;

import lombok.Data;

public class R<T> {
    private RES_CODE code;
    private String message;
    private T data;

    public RES_CODE getCode() { return code; }
    public void setCode(RES_CODE code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
    public static R<Void> ok() {
        return send(RES_CODE.SUCCESS,"success",null);
    }
    public static<T> R<T> ok(T data) {
        return send(RES_CODE.SUCCESS,"success",data);
    }
    public static R<Void> fail() {
        return send(RES_CODE.FAIL, "fail",null);
    }
    public static<T> R<T> fail(T data) {
        return send(RES_CODE.FAIL,"fail",data);
    }

    public static <T> R<T> send(RES_CODE code, String message, T data) {
        R<T> r = new R<T>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
}
