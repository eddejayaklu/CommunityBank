package com.mybank.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@ConfigurationProperties(prefix = "loans")
public class LoansInfoDataDto {
    private String message;
    private Map<String,String> contactDetails;
    private List<String> onCallSupport;
}
