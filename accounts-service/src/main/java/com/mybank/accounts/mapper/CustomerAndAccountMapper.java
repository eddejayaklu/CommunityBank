package com.mybank.accounts.mapper;

import com.mybank.accounts.dto.AccountsDto;
import com.mybank.accounts.dto.CustomerAndAccountDto;
import com.mybank.accounts.dto.CustomerDto;
import com.mybank.accounts.entity.Accounts;
import com.mybank.accounts.entity.Customer;

public class CustomerAndAccountMapper {

    public static CustomerAndAccountDto mapToCustomerAndAccountDto(Customer customer, Accounts accounts) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());

        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());

        CustomerAndAccountDto customerAndAccountDto = new CustomerAndAccountDto();
        customerAndAccountDto.setCustomer(customerDto);
        customerAndAccountDto.setAccount(accountsDto);

        return customerAndAccountDto;
    }


    public static void mapToCustomerAndAccountsEntities(CustomerAndAccountDto customerAndAccountDto, Customer customer, Accounts accounts) {
        CustomerDto customerDto = customerAndAccountDto.getCustomer();
        if (customerDto != null) {
            customer.setName(customerDto.getName());
            customer.setEmail(customerDto.getEmail());
            customer.setMobileNumber(customerDto.getMobileNumber());
        }

        AccountsDto accountsDto = customerAndAccountDto.getAccount();
        if (accountsDto != null) {
            accounts.setAccountNumber(accountsDto.getAccountNumber());
            accounts.setAccountType(accountsDto.getAccountType());
            accounts.setBranchAddress(accountsDto.getBranchAddress());
        }

    }
}
