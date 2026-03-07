package com.thepunecoder.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @NotEmpty(message = "Account Number cannot be empty or null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digit")
    private Long accountNumber;

    @NotEmpty(message = "Account Type cannot be empty or null")
    private String accountType;

    @NotEmpty(message = "Branch Address cannot be empty or null")
    private String branchAddress;
}
/*
What is the standard for valiating the fields in a DTO class in Java?
In Java, the standard for validating fields in a DTO (Data Transfer Object) class is to use the Bean Validation API (JSR 380) along with annotations from the javax.validation.constraints package
. This allows you to specify validation rules directly on the fields of the DTO class. Common annotations include @NotEmpty, @Size, @Email, @Pattern,
and others, which help ensure that the data being transferred meets certain criteria before it is processed further in the application.
Additionally, you can use custom validation annotations if needed for more complex validation logic.


Which dependency is required to use Bean Validation API in a Spring Boot application?
To use the Bean Validation API in a Spring Boot application, you need to include the following dependency in your build configuration (e.g., pom.xml for Maven):
For Maven:
```xml<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
For Gradle:
```groovyimplementation 'org.springframework.boot:spring-boot-starter-validation'
```
This dependency includes the necessary libraries for Bean Validation, such as Hibernate Validator, which is the reference implementation of the Bean Validation API. With this dependency, you can use validation annotations in your DTO classes and have Spring Boot automatically validate the incoming data when it is bound to your DTOs in controller methods.

What is JSR 380 in Java?
JSR 380, also known as Bean Validation 2.0, is a specification in Java that defines a standard way to validate JavaBeans. It provides a set of annotations and
interfaces for validating the properties of Java objects, such as DTOs (Data Transfer Objects), entities, and other JavaBeans. JSR 380 allows developers to
specify validation constraints on fields, methods, and classes, and it provides a mechanism for validating these constraints at runtime.
The specification is part of the Java EE (Enterprise Edition) platform and is commonly used in Spring Boot applications for validating user input and ensuring data integrity.
The reference implementation of JSR 380 is Hibernate Validator, which is widely used in Java applications for performing validation based on the defined constraints.
 */