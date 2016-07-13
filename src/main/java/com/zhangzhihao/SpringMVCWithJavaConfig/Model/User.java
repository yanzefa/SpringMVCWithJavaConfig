package com.zhangzhihao.SpringMVCWithJavaConfig.Model;


import com.zhangzhihao.SpringMVCWithJavaConfig.Annotation.AuthorityType;
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
public class User implements Serializable {
    @Id
    @NotNull
    private String userName;
    @NotNull
    private String passWord;
    @NotNull
    private AuthorityType authorityType;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", optional = true,fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private BankCard bankCard;

    public User() {
    }

    public User(String userName, String passWord, AuthorityType authorityType, BankCard bankCard) {
        this.userName = userName;
        this.passWord = passWord;
        this.authorityType = authorityType;
        this.bankCard = bankCard;
    }

    public User(String userName, String passWord, AuthorityType authorityType) {
        this.userName = userName;
        this.passWord = passWord;
        this.authorityType = authorityType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", authorityType=" + authorityType +
                '}';
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public AuthorityType getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(AuthorityType authorityType) {
        this.authorityType = authorityType;
    }
}
