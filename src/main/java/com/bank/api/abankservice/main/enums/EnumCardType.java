package com.bank.api.abankservice.main.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumCardType {

    INSTALLMENT(2), CASHBACK(3), SIMPLE(1);

    public int value;

}
