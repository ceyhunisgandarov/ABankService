package com.bank.api.abankservice.main.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumCreditType {

    BUSINESS(2), MORTGAGE(3), SIMPLE(1);

    public int value;

}
