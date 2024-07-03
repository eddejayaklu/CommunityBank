package com.mybank.accounts;

import com.mybank.accounts.dto.AccountInfoDataDto;
import com.mybank.accounts.entity.Accounts;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountInfoDataDto.class})
@OpenAPIDefinition(info=
		@Info(title="Accounts microservices Rest API Documentation",
			  	description = "",
			  	version = "v1",
				contact = @Contact(name = "Jayavardhan",email = "jayaedde@gmail.com"),
				license = @License(name = "Apache 2.0")))

public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
