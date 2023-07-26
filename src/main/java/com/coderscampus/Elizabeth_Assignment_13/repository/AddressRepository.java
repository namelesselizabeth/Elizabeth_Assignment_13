package com.coderscampus.Elizabeth_Assignment_13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.Elizabeth_Assignment_13.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
