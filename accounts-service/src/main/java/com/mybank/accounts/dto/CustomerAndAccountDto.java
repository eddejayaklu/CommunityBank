package com.mybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerAndAccountDto {
    @Schema(
            description = " Details of the Customer"
    )
    private CustomerDto customer;
    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto Account;
}
