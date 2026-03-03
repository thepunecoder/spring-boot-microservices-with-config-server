package com.thepunecoder.accounts.mapper;

import com.thepunecoder.accounts.dto.CustomerDto;
import com.thepunecoder.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
    
}
// This class is used for mapping Customer entity to CustomerDto and vice versa.
//ModelMapper library can also be used for mapping purpose.
//Mapstruct is another library that can be used for mapping purpose.