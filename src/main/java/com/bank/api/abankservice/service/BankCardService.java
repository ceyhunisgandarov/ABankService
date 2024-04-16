package com.bank.api.abankservice.service;


import com.bank.api.abankservice.entity.request.ReqBankCard;
import com.bank.api.abankservice.entity.response.RespBankCard;
import com.bank.api.abankservice.entity.response.RespTransaction;
import com.bank.api.abankservice.main.response.Response;

public interface BankCardService {

    Response<RespBankCard> getCard(ReqBankCard reqBankCard);

    Response<RespTransaction> payToCard(ReqBankCard reqBankCard);
}
