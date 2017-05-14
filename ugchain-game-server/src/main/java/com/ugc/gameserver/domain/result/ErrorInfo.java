package com.ugc.gameserver.domain.result;

public enum ErrorInfo {

    TOKEN_NOTEXISTS(4001, "token不存在"),
    USERNAME_NULL(4002, "username为null"),
    ON_SELLING(4003, "token待售中"),
    SERVER_ERROR(9001,"服务器错误");

    private int errorCode;
    private String errorMessage;

    private ErrorInfo(int code, String message) {
        this.errorCode = code;
        this.errorMessage = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
