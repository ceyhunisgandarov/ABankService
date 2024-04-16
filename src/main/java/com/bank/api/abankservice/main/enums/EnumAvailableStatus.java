package com.bank.api.abankservice.main.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumAvailableStatus {

    ACTIVE(1), DEACTIVE(0);

    public int value;

}
