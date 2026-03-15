package com.thepunecoder.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter@Setter@ToString
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}

/*
What is EntityListener ?
An EntityListener in JPA is a class that contains callback methods which are invoked in response to certain lifecycle events of an entity. These events include
actions such as persisting, updating, removing, and loading an entity. By using EntityListeners, you can define custom behavior that should occur when these
events happen, such as auditing, logging, or modifying the entity's state before it is persisted or updated in the database.

How we can pass custom date format ?
You can pass a custom date format in Spring Boot by using the @JsonFormat annotation from the Jackson library. This annotation allows you to specify the
desired date format when serializing and deserializing date fields in your entities. For example:
```java
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

Give Example yyyymmdd ?
public class MyEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDateTime myDate;
}
In this example, the myDate field will be serialized and deserialized using the "yyyyMMdd" format, which represents the date in the year-month-day format without
any separators. When you send or receive JSON data for this entity, the date will be formatted as a string in the specified format.
For instance, if the date is January 1, 2024, it will be represented as "20240101" in the JSON data.

 */