package com.bank.api.abankservice.security.service;

import com.bank.api.abankservice.entity.Customer;
import com.bank.api.abankservice.main.response.Response;
import com.bank.api.abankservice.security.model.ReqAuth;
import com.bank.api.abankservice.security.model.RespAuth;

public interface AuthService {

    Response<RespAuth> login(ReqAuth request);

}
