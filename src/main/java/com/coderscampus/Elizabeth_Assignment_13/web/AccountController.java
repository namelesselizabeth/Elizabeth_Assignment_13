package com.coderscampus.Elizabeth_Assignment_13.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.Elizabeth_Assignment_13.domain.Account;
import com.coderscampus.Elizabeth_Assignment_13.domain.User;
import com.coderscampus.Elizabeth_Assignment_13.service.AccountService;
import com.coderscampus.Elizabeth_Assignment_13.service.UserService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;

	@GetMapping("/users/{userId}/accounts/{accountId}")
	public String getAccount(ModelMap model, @PathVariable Long accountId) {
		Account account = accountService.findById(accountId);
		model.put("accounts", account);
		return "accounts";
	}

	@PostMapping("/users/{userId}/accounts/{accountId}")
	public String postAccount(Account account, @PathVariable Long userId, @PathVariable Long accountId) {
		account.setAccountId(accountId);
		accountService.saveAccount(account);
		return "redirect:/users/" + userId + "/accounts/" + account.getAccountId();
	}
	
	@PostMapping("/users/{userId}/accounts")
	public String createAccount(@PathVariable Long userId) {
		User user = userService.findById(userId);
		Account account = new Account();
		account.setAccountName("Account #" + user.getAccounts().size());
		user.getAccounts().add(account);
		account.getUsers().add(user);
		accountService.saveAccount(account);
		
		return "redirect:/users/" + userId;
	}
}
