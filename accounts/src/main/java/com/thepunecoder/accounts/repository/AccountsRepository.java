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
The @Transactional annotation in Spring is used to define the scope of a single database transaction. It can be applied to a method or a class to
indicate that the methods within should be executed within a transaction context.
When a method annotated with @Transactional is called, Spring creates a new transaction or joins an existing one, ensuring that all operations within the method
are completed successfully before committing the transaction.

Is this database related only ?
While @Transactional is commonly used in database-related operations to manage transactions, it can also be applied to other types of operations that require
transactional behavior, such as messaging or file operations. However, its primary use case is in the context of database transactions.

Can we have business logic in the method and apply @transactional ?
Yes, you can have business logic in a method annotated with @Transactional. The @Transactional annotation ensures that all operations within the method are executed
within a transaction context, which can include both database operations and business logic. However, it's important to ensure that the business logic does not
interfere with the transactional behavior, such as by throwing exceptions that could cause the transaction to roll back unexpectedly.

What are the types of transactions available in Spring?
Spring supports several types of transactions, including:
1. Programmatic Transactions: Managed through code using the PlatformTransactionManager interface.
2. Declarative Transactions: Managed through annotations like @Transactional, allowing for a more declarative approach to transaction management.
3. Nested Transactions: Supported through the use of the PROPAGATION_NESTED propagation behavior, allowing for transactions to be nested within other transactions.

can you elaborate more on these types with example ?
1. Programmatic Transactions:
```java
public void performTransaction() {
    TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    try {
        // Perform database operations
        transactionManager.commit(status);
    } catch (Exception e) {
        transactionManager.rollback(status);
        throw e;
    }
}
```
2. Declarative Transactions:
```java@Service
public class MyService {
    @Transactional
    public void performTransaction() {
        // Perform database operations
    }
}
```
3. Nested Transactions:
```java
public void outerTransaction() {
    TransactionStatus outerStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
    try {
        // Perform outer database operations
        innerTransaction();
        transactionManager.commit(outerStatus);
    } catch (Exception e) {
        transactionManager.rollback(outerStatus);
        throw e;
    }
}

What is the difference between Programmatic Transaction and Nested Transactions ?
Programmatic Transactions are managed through code, where you explicitly begin, commit, and roll back transactions using the TransactionStatus and PlatformTransactionManager interfaces. Nested Transactions, on the
other hand, allow for transactions to be nested within other transactions. This means that if an inner transaction fails, it can roll back without affecting the outer transaction, depending on the propagation behavior defined.

What are the propogation methods ?
Spring provides several propagation behaviors for transactions, including:
1. PROPAGATION_REQUIRED: The default behavior, where the method will join an existing transaction if one exists, or create a new transaction if none exists.
2. PROPAGATION_SUPPORTS: The method will join an existing transaction if one exists, but will execute non-transactionally if no transaction exists.
3. PROPAGATION_MANDATORY: The method must join an existing transaction, and will throw an exception if no transaction exists.
4. PROPAGATION_REQUIRES_NEW: The method will always create a new transaction, suspending any existing transaction if one exists.
5. PROPAGATION_NOT_SUPPORTED: The method will execute non-transactionally, suspending any existing transaction if one exists.
6. PROPAGATION_NEVER: The method will execute non-transactionally, and will throw an exception if a transaction exists.

Give an example of propogation methods ?
```java@Service
public class MyService {
    @Transactional(propagation = Propagation.REQUIRED)
    public void methodA() {
        // This method will join an existing transaction or create a new one if none exists
    }

@Modifying annotation in Spring Data JPA is used to annotate query methods that perform insert, update, or delete operations.

What will happen if we are not provide @Modifying annotation ?
If the @Modifying annotation is not provided on a method like deleteByCustomerId(Long customerId) in a Spring Data JPA repository, the framework will treat the query as a read-only operation (SELECT) rather than a modifying one (DELETE, UPDATE, or INSERT). This can lead to several issues:
Runtime Exception: Attempting to execute the method will likely throw an InvalidDataAccessApiUsageException or a similar error, because Spring Data JPA expects read-only queries to return data, but the method signature (e.g., void) indicates no return value. The error message might indicate that the query is not recognized as a modifying query.
No Data Modification: Even if no exception occurs, the underlying database operation (such as deleting records) will not be executed. The query might be ignored or fail silently, leaving the data unchanged.
Potential Transaction Issues: Without @Modifying, the method may not properly participate in transactional contexts, potentially causing inconsistencies if the operation is part of a larger transaction.

What is JpaRepository in Spring Data JPA?
JpaRepository is a JPA specific extension of the Repository interface in Spring Data JPA. It provides JPA related methods such as flushing the persistence context and delete records in a batch.
It also includes methods for CRUD operations and pagination and sorting capabilities.

Difference between CrudRepository and JpaRepository?
CrudRepository provides CRUD functions, while JpaRepository extends CrudRepository and adds JPA-specific methods such as flushing the persistence context and deleting records in a batch.

*/