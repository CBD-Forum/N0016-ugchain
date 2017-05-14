package com.ugc.gameserver.domain;

import java.util.Date;

/**
 * Created by fanjl on 2017/4/27.
 */
public class DermaOrder {
    private int orderId;
    private String token;
    private String seller;
    private int gameId;

    private Date createTime;
    private Date updateTime;
    private int status;
    private Derma derma;

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Derma getDerma() {
        return this.derma;
    }

    public void setDerma(Derma derma) {
        this.derma = derma;
    }

    public String getSeller() {
        return this.seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getGameId() {
        return this.gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
