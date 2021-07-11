package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class Transaction {
	
	@NotNull
	private Long accountFromId;

	@NotNull
	private Long accountToId;

	@NotNull
	@Min(value = 0, message = "Transfer amount can not be less than zero")
	private BigDecimal amount;

	@JsonCreator
	public Transaction(@NotNull @JsonProperty("accountFromId") Long accountFromId,
					   @NotNull @JsonProperty("accountToId") Long accountToId,
					   @NotNull @Min(value = 0, message = "Transfer amount can not be less than zero") @JsonProperty("amount") BigDecimal amount) {
		super();
		this.accountFromId = accountFromId;
		this.accountToId = accountToId;
		this.amount = amount;
	}
	
	@JsonCreator
	public Transaction() {
		super();
	}

	public Long getAccountFromId() {
		return accountFromId;
	}

	public void setAccountFromId(Long accountFromId) {
		this.accountFromId = accountFromId;
	}

	public Long getAccountToId() {
		return accountToId;
	}

	public void setAccountToId(Long accountToId) {
		this.accountToId = accountToId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountFromId == null) ? 0 : accountFromId.hashCode());
		result = prime * result + ((accountToId == null) ? 0 : accountToId.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
		Transaction other = (Transaction) obj;
		if (accountFromId == null) {
			if (other.accountFromId != null)
				return false;
		} else if (!accountFromId.equals(other.accountFromId))
			return false;
		if (accountToId == null) {
			if (other.accountToId != null)
				return false;
		} else if (!accountToId.equals(other.accountToId))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		return true;
	}

}
