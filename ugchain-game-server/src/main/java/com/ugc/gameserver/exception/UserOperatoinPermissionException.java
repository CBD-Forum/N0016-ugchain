package com.ugc.gameserver.exception;

/**
 * Created by yuanshichao on 2016/12/6.
 */
public class UserOperatoinPermissionException extends RuntimeException {

    public UserOperatoinPermissionException() {
    }

    public UserOperatoinPermissionException(String message) {
        super(message);
    }

    public UserOperatoinPermissionException(int userId, String oper) {
        super(String.format("User operation %s Permission denied [userId: %d]", oper, userId));
    }
}
