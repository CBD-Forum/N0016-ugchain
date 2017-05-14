package com.ugc.gameserver.domain;

/**
 * Created by fanjl on 2017/4/7.
 */
public enum OrderTypeEnum {
    RECHARGE(1),
    WITHDRAW(2);

    private int id;

    OrderTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static OrderTypeEnum fromValue(int id) {
        for (OrderTypeEnum status : OrderTypeEnum.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }

    public static OrderTypeEnum fromString(String str) {
        for (OrderTypeEnum role : OrderTypeEnum.values()) {
            if (role.name().equals(str)) {
                return role;
            }
        }
        throw new IllegalArgumentException(str);
    }
}
