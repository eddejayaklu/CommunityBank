package com.mybank.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min=5,max = 30,message = "Length of the name should be min 5 and max 30")
    private String name;

    @NotEmpty(message = "email cannot be null or empty")
    @Email(message = "email address should be valid")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;
}
