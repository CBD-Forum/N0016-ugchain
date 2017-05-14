package com.ugc.gameserver.domain;

import com.ugc.gameserver.domain.result.GameDes;

import java.util.Date;
import java.util.List;

/**
 * Created by fanjl on 2017/4/25.
 */
public class UserToken {
    private int userTokenId;
    private String userName;
    private String token;
    private String data;
    private List<String> derma;
    private Date createTime;
    private Date updateTime;
    private int status;
    private GameDes gameDes;
    private List<Derma> dermaList;

    public GameDes getGameDes() {
        return this.gameDes;
    }

    public void setGameDes(GameDes gameDes) {
        this.gameDes = gameDes;
    }

    public int getUserTokenId() {
        return this.userTokenId;
    }

    public void setUserTokenId(int userTokenId) {
        this.userTokenId = userTokenId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
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

    public List<String> getDerma() {
        return this.derma;
    }

    public void setDerma(List<String> derma) {
        this.derma = derma;
    }

    public List<Derma> getDermaList() {
        return this.dermaList;
    }

    public void setDermaList(List<Derma> dermaList) {
        this.dermaList = dermaList;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
