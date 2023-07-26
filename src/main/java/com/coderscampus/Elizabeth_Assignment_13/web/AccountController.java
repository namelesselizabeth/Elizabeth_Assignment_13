package com.coderscampus.Elizabeth_Assignment_13.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	@GetMapping("/users/{userId}/accounts")
	public String getAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
		User user = userService.findById(userId);
		Account account = accountService.findById(accountId);
		model.put("user", user);
		model.put("accounts", account);
		return "accounts";
	}
//	
	//@GetMapping(/users/{userId}/accounts)
	
//	@GetMapping("/users/{userId}/accounts")
//	List<>
}
