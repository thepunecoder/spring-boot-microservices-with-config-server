package com.thepunecoder.accounts.service;

import com.thepunecoder.accounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    /**
     * Update existing customer account details.
     *
     * @param customerDto The customer data transfer object containing updated information.
     * @return true if the update was successful, false otherwise.
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * Delete customer account based on mobile number.
     * @param mobileNumber
     * @return
     */
    boolean deleteAccount(String mobileNumber);
}
