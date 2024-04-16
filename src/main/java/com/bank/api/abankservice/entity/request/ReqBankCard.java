package com.bank.api.abankservice.entity.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqBankCard {

    private String bankCardNumber;
    private BigDecimal amountOfPayment;
    private String senderAccountNumber;

}
