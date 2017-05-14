package com.ugc.gameserver.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by fanjl on 2017/4/7.
 */
public class BlockRecord {
    private int blockRecordId;
    private String transactionId;
    private String targetAddress;
    private BigInteger amount;
    private BigDecimal fee;
    private int nonce;
    private int type;
    private Date createTime;
    private Date updateTime;
    private int status;

    public int getBlockRecordId() {
        return this.blockRecordId;
    }

    public void setBlockRecordId(int blockRecordId) {
        this.blockRecordId = blockRecordId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTargetAddress() {
        return this.targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public BigInteger getAmount() {
        return this.amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public int getNonce() {
        return this.nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
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
