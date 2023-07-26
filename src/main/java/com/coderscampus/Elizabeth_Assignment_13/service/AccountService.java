package com.coderscampus.Elizabeth_Assignment_13.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.Elizabeth_Assignment_13.domain.Account;
import com.coderscampus.Elizabeth_Assignment_13.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;
	
	public Account saveAccount(Account account) {
		return accountRepo.save(account);
	}
	
	public Account findById(Long accountId) {
		Optional<Account> accountOpt = accountRepo.findById(accountId);
		return accountOpt.orElse(new Account());
	}
	
	public List<Account> findAll() {
		return accountRepo.findAll();
	}
}
