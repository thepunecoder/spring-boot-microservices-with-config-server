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
