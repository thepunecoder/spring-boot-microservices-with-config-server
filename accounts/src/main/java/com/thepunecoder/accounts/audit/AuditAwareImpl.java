package com.thepunecoder.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS");
    }

}

/*
What is this class used for in a Spring Boot application?
The AuditAwareImpl class is used in a Spring Boot application to provide the current auditor information for auditing purposes.
It implements the AuditorAware interface, which is part of Spring Data JPA's auditing framework.
By implementing this interface, the class can supply the current auditor (in this case, a string "ACCOUNTS_MS") whenever the auditing
framework needs to record who created or modified an entity. This is typically used in conjunction with annotations like @CreatedBy and @LastModifiedBy
in entity classes to automatically populate these fields with the auditor information during database operations.

 */