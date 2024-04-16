package com.bank.api.abankservice.entity.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespTransaction {

    private String transactionId;
    private String customerFullName;
    private String cardNumber;
    private BigDecimal amount;
    private String currency;
    private String agreementNumber;
    private String accountNumber;
    private BigDecimal remainingDept;
    private BigDecimal amountOfCredit;

}
