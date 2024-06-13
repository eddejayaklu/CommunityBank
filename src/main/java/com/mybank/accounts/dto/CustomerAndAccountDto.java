package com.mybank.accounts.dto;

import lombok.Data;

@Data
public class CustomerAndAccountDto {
    private CustomerDto customer;
    private AccountsDto Account;
}
