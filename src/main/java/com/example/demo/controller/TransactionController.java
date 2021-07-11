package com.example.demo.controller;

import com.example.demo.dto.TransferResult;
import com.example.demo.exception.AccountNotExistException;
import com.example.demo.exception.OverDraftException;
import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountsRepository;
import com.example.demo.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {
	
	private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountsRepository accountsRepository;

	@PostMapping(consumes = { "application/json" })
	public ResponseEntity transferMoney(@RequestBody @Valid Transaction request)  {

		try {
			transactionService.moneyTransfer(request);
			Optional<Account> fromBalance=accountsRepository.findByAccountNumber(request.getAccountFromId());
			Optional<Account> toBalance=accountsRepository.findByAccountNumber(request.getAccountToId());
			TransferResult result = new TransferResult();
			result.setAccountFromId(request.getAccountFromId());
			result.setAccountToId(request.getAccountToId());
			result.setFromBalance(fromBalance.get().getBalance());
			result.setToBalance(toBalance.get().getBalance());
			return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		} catch (AccountNotExistException | OverDraftException e) {
			log.error("Failed to transfer balance");
			throw e;
		}
	}
}
