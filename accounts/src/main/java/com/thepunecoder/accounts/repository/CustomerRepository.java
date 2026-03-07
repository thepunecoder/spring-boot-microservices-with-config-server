package com.thepunecoder.accounts.repository;

import com.thepunecoder.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByMobileNumber(String mobileNumber);
}

/*
 * This is the CustomerRepository interface that extends JpaRepository to provide CRUD operations for the Customer entity.
 * It includes a custom method findByMobileNumber to retrieve a customer based on their mobile number.
 * The @Repository annotation indicates that this interface is a Spring Data repository, which will be automatically implemented by Spring Data JPA.
 Difference between JPARepository and CrudRepository and ListRepository in Spring Data JPA:
 * - CrudRepository: This is the base interface for generic CRUD operations. It provides methods for saving, deleting, and finding entities. It does not include pagination and sorting capabilities.
 * - JpaRepository: This interface extends CrudRepository and adds additional methods for pagination and sorting.
 * - ListRepository: This is not a standard interface in Spring Data JPA. It may be a custom interface defined in the application for specific use cases, but it is not part of the Spring Data JPA framework.

 * */