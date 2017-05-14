package com.ugc.gameserver.domain;

/**
 * Created by fanjl on 2017/4/12.
 */
public enum AmountChangeTypeEnum {
    ADD(1),
    SUBTRACTION(2);

    private int id;

    AmountChangeTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static AmountChangeTypeEnum fromValue(int id) {
        for (AmountChangeTypeEnum status : AmountChangeTypeEnum.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }

    public static AmountChangeTypeEnum fromString(String str) {
        for (AmountChangeTypeEnum role : AmountChangeTypeEnum.values()) {
            if (role.name().equals(str)) {
                return role;
            }
        }
        throw new IllegalArgumentException(str);
    }
}
