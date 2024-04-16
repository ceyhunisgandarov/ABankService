package com.bank.api.abankservice.security.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqAuth {

    private String userName;
    private String password;

}
