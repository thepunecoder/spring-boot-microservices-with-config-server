package com.thepunecoder.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
public class Accounts extends BaseEntity{

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "account_number")
    @Id
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;
}

/*
Question:
What is the purpose of the @Entity annotation in the Accounts class, and how do the Lombok annotations like @Getter, @Setter, @ToString, @AllArgsConstructor, and @NoArgsConstructor enhance the functionality of this JPA entity?
Answer:
The @Entity annotation in the Accounts class indicates that this class is a JPA entity, meaning it is mapped to a database table. This allows the class to be managed by the JPA provider, enabling CRUD operations and other database interactions.
The Lombok annotations enhance the functionality of this JPA entity by automatically generating boilerplate code, reducing the amount of manual coding required. Specifically:
- @Getter and @Setter generate getter and setter methods for all fields, allowing easy access and modification of the entity's properties.
- @ToString generates a toString() method that provides a string representation of the object, useful for debugging and logging.
- @AllArgsConstructor generates a constructor that takes all fields as parameters, facilitating the creation of fully initialized instances.
- @NoArgsConstructor generates a default constructor with no parameters, which is required by JPA for entity instantiation.
Overall, these annotations streamline the development process and improve code readability.
*/