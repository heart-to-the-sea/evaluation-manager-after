package com.em.parent.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Data
public class R<T> {
    public RES_CODE code;
    public String message;
    public T data;
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
