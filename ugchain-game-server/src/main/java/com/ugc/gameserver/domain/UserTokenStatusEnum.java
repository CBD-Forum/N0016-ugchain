package com.ugc.gameserver.domain;

/**
 * Created by fanjl on 2017/4/25.
 */
public enum UserTokenStatusEnum {
    USEING(1,"使用中"),
    FROZEN(2,"冻结中"),
    DELETED(3,"已删除"),
    DEALING(4,"交易中");
    private int id;
    private String description;

    UserTokenStatusEnum(int id,String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public static UserTokenStatusEnum fromValue(int id) {
        for (UserTokenStatusEnum status : UserTokenStatusEnum.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }

    public static UserTokenStatusEnum fromString(String str) {
        for (UserTokenStatusEnum role : UserTokenStatusEnum.values()) {
            if (role.name().equals(str)) {
                return role;
            }
        }
        throw new IllegalArgumentException(str);
    }


}
