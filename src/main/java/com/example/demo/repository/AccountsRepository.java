package com.example.demo.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Account;

@Transactional(readOnly = true)
public interface AccountsRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByAccountNumber(Long accountNumber);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	@Query("SELECT a FROM Account a WHERE a.accountNumber = ?1")
	Optional<Account> getAccount(Long id);

	
}
