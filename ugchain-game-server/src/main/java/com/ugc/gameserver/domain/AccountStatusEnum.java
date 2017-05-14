package com.ugc.gameserver.domain;


/**
 * Created by fanjl on 2017/4/7.
 */
public enum AccountStatusEnum {
    NORMAL(1),
    FROZEN(2),
    STOP(3),
	REPORT(4),
	INVALID(5);
	
    private int id;

    AccountStatusEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static AccountStatusEnum fromValue(int id) {
        for (AccountStatusEnum status : AccountStatusEnum.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }

    public static AccountStatusEnum fromString(String str) {
        for (AccountStatusEnum role : AccountStatusEnum.values()) {
            if (role.name().equals(str)) {
                return role;
            }
        }
        throw new IllegalArgumentException(str);
    }

}
