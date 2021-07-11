package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="ACCOUNTS")
public class Account {

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Id
	@Column(name = "ACCOUNTID")
	private Long accountNumber;
	
	@NotNull
	@Column(name = "BALANCE")
	@Min(value = 0, message = "account balance must be positive")
	private BigDecimal balance;


	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Account() {
		super();
	}

	public Account(Long accountNumber,
			@NotNull @Min(value = 0, message = "account balance must be positive") BigDecimal balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		return true;
	}

}
