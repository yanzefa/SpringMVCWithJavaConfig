package com.zhangzhihao.SpringMVCWithJavaConfig.Model;


import com.zhangzhihao.SpringMVCWithJavaConfig.Annotation.AuthorityType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
	@Id
	private String userName;
	private String passWord;
	private AuthorityType authorityType;

	public User() {
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
