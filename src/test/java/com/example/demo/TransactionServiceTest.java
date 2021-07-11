package com.example.demo;

import com.example.demo.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import com.example.demo.exception.AccountNotExistException;
import com.example.demo.exception.OverDraftException;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountsRepository;
import com.example.demo.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	@Mock
	AccountsRepository accRepo;
	
	@InjectMocks
	TransactionService transactionService;

	@Test
	public void testTransferBalance()  {
		Long accountFromId = 1L;
		Long accountFromTo = 2L;
		BigDecimal amount = new BigDecimal(10);
		
		Transaction request = new Transaction();
		request.setAccountFromId(accountFromId);
		request.setAccountToId(accountFromTo);
		request.setAmount(amount);
		
		Account accFrom = new Account(accountFromId, BigDecimal.TEN);
		Account accTo = new Account(accountFromId, BigDecimal.TEN);

		when(accRepo.getAccount(accountFromId)).thenReturn(Optional.of(accFrom));
		when(accRepo.getAccount(accountFromTo)).thenReturn(Optional.of(accTo));

		transactionService.moneyTransfer(request);
		
		assertEquals(BigDecimal.ZERO, accFrom.getBalance());
		assertEquals(BigDecimal.TEN.add(BigDecimal.TEN), accTo.getBalance());
	}
	
	@Test(expected = OverDraftException.class)
	public void testOverdraftBalance() throws OverDraftException, AccountNotExistException {
		Long accountFromId = 1L;
		Long accountFromTo = 2L;
		BigDecimal amount = new BigDecimal(20);
		
		Transaction request = new Transaction();
		request.setAccountFromId(accountFromId);
		request.setAccountToId(accountFromTo);
		request.setAmount(amount);
		
		Account accFrom = new Account(accountFromId, BigDecimal.TEN);
		Account accTo = new Account(accountFromId, BigDecimal.TEN);

		when(accRepo.getAccount(accountFromId)).thenReturn(Optional.of(accFrom));
		when(accRepo.getAccount(accountFromTo)).thenReturn(Optional.of(accTo));

		transactionService.moneyTransfer(request);
	}
}
