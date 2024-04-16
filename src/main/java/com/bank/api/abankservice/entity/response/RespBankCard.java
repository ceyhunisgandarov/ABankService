package com.bank.api.abankservice.entity.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespBankCard {

    private String customerName;
    private String customerSurname;
    private String currency;
    private String accountNumber;
    private BigDecimal maxLimit;
    private String cardNumber;

}
