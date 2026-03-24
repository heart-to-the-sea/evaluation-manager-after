package com.em.parent.common;


public class R<T> {
    private String code;
    private String message;
    private T data;

    public String getCode() { return code; }
    public R setCode(RES_CODE code) { this.code = code.getValue();  return this;}
    public String getMessage() { return message; }
    public R setMessage(String message) {
        this.message = message;
        return this;
    }
    public T getData() { return data; }
    public R<T> setData(T data) { this.data = data; return this;}
    public static R<Void> ok() {
        return send(RES_CODE.SUCCESS,"success",null);
    }
    public static<T> R<T> ok(T data) {
        return send(RES_CODE.SUCCESS,"success",data);
    }
    public static R<Object> fail() {
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
