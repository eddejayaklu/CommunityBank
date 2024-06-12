package com.mybank.accounts.service.impl;


import com.mybank.accounts.constants.AccountsConstants;
import com.mybank.accounts.dto.CustomerDto;
import com.mybank.accounts.entity.Accounts;
import com.mybank.accounts.entity.Customer;
import com.mybank.accounts.exception.CustomerAlreadyExistsException;
import com.mybank.accounts.mapper.CustomerMapper;
import com.mybank.accounts.repository.AccountRepository;
import com.mybank.accounts.repository.CustomerRepository;
import com.mybank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;



    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with registered mobile number " + customerDto.getMobileNumber());
        }

        customer.setCreateAt(LocalDateTime.now());
        customer.setCreateBy("jaya");

        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }


    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        newAccount.setCreateAt(LocalDateTime.now());
        newAccount.setCreateBy("jaya");

        return newAccount;
    }
}
