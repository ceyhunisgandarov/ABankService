package com.bank.api.abankservice.service;

import com.bank.api.abankservice.entity.request.ReqCredit;
import com.bank.api.abankservice.entity.response.RespCredit;
import com.bank.api.abankservice.entity.response.RespTransaction;
import com.bank.api.abankservice.main.response.Response;

public interface CreditService {

    Response<RespCredit> getCredit(ReqCredit reqCredit);

    Response<RespTransaction> payCredit(ReqCredit reqCredit);
}
