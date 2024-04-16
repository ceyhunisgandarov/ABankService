package com.bank.api.abankservice.entity.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqCredit {

    private String agreementNumber;
    private Date customerDob;
    private BigDecimal payAmount;
    private String senderAccount;

}
