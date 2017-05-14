package com.ugc.gameserver.domain;

/**
 * Created by fanjl on 2017/4/27.
 */
public enum DermaListEnum {
    DERMA_1(1,"皮肤1",100),
    DERMA_2(2,"皮肤2",200),
    DERMA_3(3,"皮肤3",300),
    DERMA_4(4,"皮肤4",400),
    DERMA_5(5,"皮肤5",500);

    private int id;
    private String name;
    private int prices;

    DermaListEnum(int id,String name,int prices) {
        this.id = id;
        this.prices = prices;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public int getPrices() {
        return this.prices;
    }

    public String getName() {
        return this.name;
    }

    public static DermaListEnum fromValue(int id) {
        for (DermaListEnum status : DermaListEnum.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }

    public static DermaListEnum fromString(String str) {
        for (DermaListEnum role : DermaListEnum.values()) {
            if (role.name().equals(str)) {
                return role;
            }
        }
        throw new IllegalArgumentException(str);
    }

    public static boolean contains(int id) {
        for (DermaListEnum role : DermaListEnum.values()) {
            if (role.getId()==id) {
                return true;
            }
        }
        return  false;

    }



}
