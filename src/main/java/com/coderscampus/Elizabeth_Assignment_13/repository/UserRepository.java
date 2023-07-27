package com.coderscampus.Elizabeth_Assignment_13.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coderscampus.Elizabeth_Assignment_13.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUsername(String username);
	List<User> findByName(String name);
	List<User> findByNameAndUsername(String name, String username);

	@Query("select u from User u"
			+ " left join fetch u.accounts"
			+ " left join fetch u.address")
		Set<User> findAllUsersWithAccountsAndAddresses();

	@Query("select u from User u where username = :username")
	List<User> findExactlyOneUserByUsername(String username);

	@Query("select u from User u"
			+ " left join fetch u.accounts"
	        + " left join fetch u.address"
			+ " where u.userId = :userId")
		Optional<User> findByIdWithAccounts(Long userId);
}
