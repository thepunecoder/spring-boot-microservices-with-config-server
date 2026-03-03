package com.thepunecoder.accounts.repository;

import com.thepunecoder.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}

/*

What is @Repository annotation in Spring?
The @Repository annotation in Spring is a specialization of the @Component annotation that indicates that the class is a Data Access Object (DAO) responsible for interacting with the database.
It provides a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects.
It also enables exception translation, converting database-specific exceptions into Spring's DataAccessException hierarchy.

What is @Transactional annotation in Spring?
The @Transactional annotation in Spring is used to define the scope of a single database transaction. It can be applied to a method or a class to indicate that the methods within should be executed within a transaction context.
When a method annotated with @Transactional is called, Spring creates a new transaction or joins an existing one, ensuring that all operations within the method are completed successfully before committing the transaction.

@Modifying annotation in Spring Data JPA is used to annotate query methods that perform insert, update, or delete operations.

What is JpaRepository in Spring Data JPA?
JpaRepository is a JPA specific extension of the Repository interface in Spring Data JPA. It provides JPA related methods such as flushing the persistence context and delete records in a batch.
It also includes methods for CRUD operations and pagination and sorting capabilities.

Difference between CrudRepository and JpaRepository?
CrudRepository provides CRUD functions, while JpaRepository extends CrudRepository and adds JPA-specific methods such as flushing the persistence context and deleting records in a batch.

*/