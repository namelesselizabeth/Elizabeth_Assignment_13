package com.coderscampus.Elizabeth_Assignment_13.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.Elizabeth_Assignment_13.domain.Account;
import com.coderscampus.Elizabeth_Assignment_13.domain.Address;
import com.coderscampus.Elizabeth_Assignment_13.domain.User;
import com.coderscampus.Elizabeth_Assignment_13.repository.AccountRepository;
import com.coderscampus.Elizabeth_Assignment_13.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	public Set<User> findAll() {
		return userRepo.findAllUsersWithAccountsAndAddresses();
	}
	
	public User findById(Long userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		return userOpt.orElse(new User());
	}
	
	public User saveUser(User user) {
		
		if (user.getUserId() == null) {
			Account checking = new Account();
			checking.setAccountName("Checking Account");
			checking.getUsers().add(user);
			Account savings = new Account();
			savings.setAccountName("Savings Account");
			savings.getUsers().add(user);
			
			user.getAccounts().add(checking);
			user.getAccounts().add(savings);
			accountRepo.save(checking);
			accountRepo.save(savings);
		}

		return userRepo.save(user);
	}
	
	public User saveUser(User user, Address address) {
		return userRepo.save(user);
	}
		
	public void delete(Long userId) {
		userRepo.deleteById(userId);
	}
	
	//Custom Queries with JPQL
	public List<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public List<User> findByNameAndUsername(String name, String username) {
		return userRepo.findByNameAndUsername(name, username);
	}
	public User findByIdWithAccounts(Long userId) {
		Optional<User> userOpt = userRepo.findByIdWithAccounts(userId);
		return userOpt.orElse(new User());
	}

	public AccountRepository getAccountRepo() {
		return accountRepo;
	}

	public void setAccountRepo(AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}
}
