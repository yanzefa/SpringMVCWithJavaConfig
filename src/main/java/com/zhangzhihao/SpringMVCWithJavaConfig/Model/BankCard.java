package com.zhangzhihao.SpringMVCWithJavaConfig.Model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BankCard implements Serializable {
    @Id
    @NotNull
    private String cardNumber;
    @NotNull
    private String bankName;
    @NotNull
    private String accountHolder;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "userName")
    @Fetch(FetchMode.SELECT)
    private User user;

    public BankCard(String cardNumber, String bankName, String accountHolder, User user) {
        this.cardNumber = cardNumber;
        this.bankName = bankName;
        this.accountHolder = accountHolder;
        this.user = user;
    }

    public BankCard() {
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", user=" + user +
                '}';
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
