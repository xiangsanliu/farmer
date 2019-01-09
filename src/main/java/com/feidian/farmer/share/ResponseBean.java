package com.feidian.farmer.share;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseBean {

    private Integer status;
    private String msg;
    private Object content;

    private ResponseBean() {

    }

    public static ResponseBean build() {
        return new ResponseBean();
    }

    public static ResponseBean ok() {
        return new ResponseBean(200, null, null);
    }

    public static ResponseBean ok(String msg, Object content) {
        return new ResponseBean(200, msg, content);
    }

    public static ResponseBean ok(String msg) {
        return new ResponseBean(200, msg, null);
    }

    public static ResponseBean error(String msg, Object content) {
        return new ResponseBean(500, msg, content);
    }

    public static ResponseBean error(String msg) {
        return new ResponseBean(500, msg, null);
    }

    public static ResponseBean auth() {
        return new ResponseBean(401, null, null);
    }

    public static ResponseBean forbid() {
        return new ResponseBean(403, null, null);
    }

    private ResponseBean(Integer status, String msg, Object content) {
        this.status = status;
        this.msg = msg;
        this.content = content;
    }

}
