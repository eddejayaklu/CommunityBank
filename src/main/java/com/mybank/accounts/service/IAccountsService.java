package com.mybank.accounts.service;

import com.mybank.accounts.dto.CustomerAndAccountDto;
import com.mybank.accounts.dto.CustomerDto;

public interface IAccountsService {
    void createAccount(CustomerDto customerDto);
    CustomerAndAccountDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerAndAccountDto customerAndAccountDto);
    boolean deleteAccount(String mobileNumber);

}
