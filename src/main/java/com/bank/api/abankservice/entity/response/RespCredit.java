package com.bank.api.abankservice.entity.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespCredit {

    private String agreementNumber;
    private String customerFullName;
    private BigDecimal creditAmount;
    private BigDecimal remainingDept;
    private String accountNumber;
    private String currency;

}
