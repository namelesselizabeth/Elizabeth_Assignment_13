package com.coderscampus.Elizabeth_Assignment_13.web;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.Elizabeth_Assignment_13.domain.Address;
import com.coderscampus.Elizabeth_Assignment_13.domain.User;
import com.coderscampus.Elizabeth_Assignment_13.service.AddressService;
import com.coderscampus.Elizabeth_Assignment_13.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;

	@GetMapping("/users")
	public String getAllUsers(ModelMap model) {
		Set<User> users = userService.findAll();
		model.put("users", users);

		if (users.size() > 1) {
			return "users";
		}
		else if (!users.isEmpty()) {
		    User user = users.stream().findFirst().get();
		    if (user != null) {
		        model.put("user", user);
		        
		    }
		   return "redirect:/users/" + user.getUserId();
		}
		return null;
		
	}
		

	@GetMapping("/users/{userId}")
	public String getOneUser (ModelMap model, @PathVariable Long userId) {
		User user = userService.findByIdWithAccounts(userId);
		if(user.getAddress() == null) {
			Address address = new Address();
			address.setUser(user);
			address.setUserId(userId);
			user.setAddress(address);
			addressService.save(address);

		}

		userService.saveUser(user);
		model.put("users", Arrays.asList(user));
		model.put("user", user);
		model.put("address", user.getAddress());
		return "users";
	}

	@PostMapping("/users/{userId}")
	public String postOneUser (@PathVariable Long userId, User user) {
		if(user.getPassword().isBlank()) {
			user.setPassword(userService.findById(user.getUserId()).getPassword());
		}
		if(userService.findByIdWithAccounts(user.getUserId()) != null) {
			user.setAccounts(userService.findByIdWithAccounts(user.getUserId()).getAccounts());
		}
		
		Address address = addressService.save(user.getAddress());
		user.setAddress(address);
		address.setUser(user);
		
		userService.saveUser(user);
		return "redirect:/users/" + userId;
	}

	@GetMapping("/register")
	public String getCreateUser (ModelMap model) {
		model.put("user", new User());

		return "register";
	}

	@PostMapping("/register")
	public String postCreateUser (User user) {
		userService.saveUser(user);
		return "redirect:/register";
	}

	@PostMapping("users/{userId}/delete")
	public String deleteOneUser (@PathVariable Long userId) {
		userService.delete(userId);
		return "redirect:/users";
	}

}
