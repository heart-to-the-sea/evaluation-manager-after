package com.em.parent.common;

public enum RES_CODE {
    SUCCESS("0000"),
    FAIL("500");
    private String value;
    RES_CODE(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }
}
