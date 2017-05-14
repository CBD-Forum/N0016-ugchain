package com.ugc.gameserver.domain.result;

/**
 * Created by yuanshichao on 2016/11/17.
 */

public class Meta {
    private final int code;
    private final String message;

    public Meta(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

