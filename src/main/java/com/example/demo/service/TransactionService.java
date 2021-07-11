package com.example.demo.service;

import javax.transaction.Transactional;

import com.example.demo.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.ErrorCode;
import com.example.demo.exception.AccountNotExistException;
import com.example.demo.exception.OverDraftException;
import com.example.demo.exception.SystemException;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountsRepository;

@Service
public class TransactionService {

	@Autowired
	private AccountsRepository accountsRepository;

	@Transactional
	public void moneyTransfer(Transaction transaction) throws OverDraftException, AccountNotExistException {
		Account accountFrom = accountsRepository.getAccount(transaction.getAccountFromId())
				.orElseThrow(() -> new AccountNotExistException("Account number:" + transaction.getAccountFromId() + " does not exist.", ErrorCode.ACCOUNT_ERROR));
		
		Account accountTo = accountsRepository.getAccount(transaction.getAccountToId())
				.orElseThrow(() -> new AccountNotExistException("Account  number:" + transaction.getAccountFromId() + " does not exist.", ErrorCode.ACCOUNT_ERROR));
		
		if(accountFrom.getBalance().compareTo(transaction.getAmount()) < 0) {
			throw new OverDraftException("Account with number:" + accountFrom.getAccountNumber() + " not having enough balance to transfer.", ErrorCode.ACCOUNT_ERROR);
		}
		
		accountFrom.setBalance(accountFrom.getBalance().subtract(transaction.getAmount()));
		accountTo.setBalance(accountTo.getBalance().add(transaction.getAmount()));
		accountsRepository.save(accountFrom);
		accountsRepository.save(accountTo);

	}
}
