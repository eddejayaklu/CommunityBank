package com.mybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer"
)
public class CustomerDto {
    @Schema(
            description = "Name of the customer", example = "Eazy Bytes"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min=5,max = 30,message = "Length of the name should be min 5 and max 30")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "abc@gmail.com"
    )
    @NotEmpty(message = "email cannot be null or empty")
    @Email(message = "email address should be valid")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;
}
