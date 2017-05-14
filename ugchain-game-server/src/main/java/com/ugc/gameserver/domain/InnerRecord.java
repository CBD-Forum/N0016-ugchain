package com.ugc.gameserver.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanjl on 2017/4/7.
 */
public class InnerRecord {
    private int innerRecordId;
    private String fromAddress;
    private String toAddress;
    private BigDecimal amount;
    private int nonce;
    private Date createTime;
    private Date updateTime;
    private int status;

    public int getInnerRecordId() {
        return this.innerRecordId;
    }

    public void setInnerRecordId(int innerRecordId) {
        this.innerRecordId = innerRecordId;
    }

    public String getFromAddress() {
        return this.fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return this.toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getNonce() {
        return this.nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
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
}
