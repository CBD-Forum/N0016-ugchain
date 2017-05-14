package com.ugc.gameserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * Created by yuanshichao on 2016/12/15.
 */

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private BigDecimal fee;

    private String gethLocation;

    private String sellerAddress;

    private String rechargeAddress;

    private String tradeAddress;

    private String dasAddress;

    private String walletPassword;

    private String walletPath;

    private String gethHttpURL;

    public String getGethHttpURL() {
        return this.gethHttpURL;
    }

    public void setGethHttpURL(String gethHttpURL) {
        this.gethHttpURL = gethHttpURL;
    }

    public String getWalletPassword() {
        return this.walletPassword;
    }

    public void setWalletPassword(String walletPassword) {
        this.walletPassword = walletPassword;
    }

    public String getWalletPath() {
        return this.walletPath;
    }

    public void setWalletPath(String walletPath) {
        this.walletPath = walletPath;
    }

    public String getSellerAddress() {
        return this.sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getRechargeAddress() {
        return this.rechargeAddress;
    }

    public void setRechargeAddress(String rechargeAddress) {
        this.rechargeAddress = rechargeAddress;
    }

    public String getGethLocation() {
        return this.gethLocation;
    }

    public void setGethLocation(String gethLocation) {
        this.gethLocation = gethLocation;
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getTradeAddress() {
        return this.tradeAddress;
    }

    public void setTradeAddress(String tradeAddress) {
        this.tradeAddress = tradeAddress;
    }

    public String getDasAddress() {
        return this.dasAddress;
    }

    public void setDasAddress(String dasAddress) {
        this.dasAddress = dasAddress;
    }
}
