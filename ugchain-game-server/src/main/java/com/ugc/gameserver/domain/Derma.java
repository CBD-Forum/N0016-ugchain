package com.ugc.gameserver.domain;

/**
 * Created by fanjl on 2017/4/27.
 */
public class Derma {
    private int id;
    private String name;
    private int prices;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrices() {
        return this.prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }
}
