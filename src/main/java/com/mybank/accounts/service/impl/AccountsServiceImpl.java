package com.mybank.accounts.service.impl;


import com.mybank.accounts.constants.AccountsConstants;
import com.mybank.accounts.dto.AccountsDto;
import com.mybank.accounts.dto.CustomerAndAccountDto;
import com.mybank.accounts.dto.CustomerDto;
import com.mybank.accounts.entity.Accounts;
import com.mybank.accounts.entity.Customer;
import com.mybank.accounts.exception.CustomerAlreadyExistsException;
import com.mybank.accounts.exception.ResourceNotFoundException;
import com.mybank.accounts.mapper.CustomerAndAccountMapper;
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


        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerAndAccountDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow();
        Accounts account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow();

        return CustomerAndAccountMapper.mapToCustomerAndAccountDto(customer, account);
    }

    @Override
    public boolean updateAccount(CustomerAndAccountDto customerAndAccountDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerAndAccountDto.getAccount();

        if (accountsDto != null) {
            // Find and update the Accounts entity
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );

            // Find the Customer entity
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );

            // Map fields from DTO to entities
            CustomerAndAccountMapper.mapToCustomerAndAccountsEntities(customerAndAccountDto, customer, accounts);

            // Save the updated entities
            accountRepository.save(accounts);
            customerRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);



        return newAccount;
    }
}


