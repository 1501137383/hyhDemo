package com.loop.demo.common;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class RestResponse<T> {

    private T data;
    private int status = 200;
    private String message = "操作成功";
    boolean rel;


    public boolean isRel() {
        return rel;
    }


    public RestResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public RestResponse() {
    }

    public RestResponse(boolean rel) {
        this.rel = rel;
    }

    public RestResponse<T> rel(boolean rel) {
        this.setRel(rel);
        return this;
    }


    public RestResponse<T> data(T data) {
        this.data = data;
        return this;
    }
}
