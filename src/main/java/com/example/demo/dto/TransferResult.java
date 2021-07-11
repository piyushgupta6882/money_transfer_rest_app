package com.example.demo.dto;

import java.math.BigDecimal;

public class TransferResult {
	
	private Long accountFromId;



	private Long accountToId;

	public BigDecimal getFromBalance() {
		return fromBalance;
	}

	public void setFromBalance(BigDecimal fromBalance) {
		this.fromBalance = fromBalance;
	}

	public BigDecimal getToBalance() {
		return toBalance;
	}

	public void setToBalance(BigDecimal toBalance) {
		this.toBalance = toBalance;
	}

	private BigDecimal fromBalance;
	private BigDecimal toBalance;

	public Long getAccountFromId() {
		return accountFromId;
	}

	public Long getAccountToId() {
		return accountToId;
	}

	public void setAccountToId(Long accountToId) {
		this.accountToId = accountToId;
	}

	public void setAccountFromId(Long accountFromId) {
		this.accountFromId = accountFromId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountFromId == null) ? 0 : accountFromId.hashCode());

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
		TransferResult other = (TransferResult) obj;
		if (accountFromId == null) {
			if (other.accountFromId != null)
				return false;
		} else if (!accountFromId.equals(other.accountFromId))
			return false;

		return true;
	}
	
}
