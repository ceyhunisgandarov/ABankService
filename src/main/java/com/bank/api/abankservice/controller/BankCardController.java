package com.bank.api.abankservice.controller;

import com.bank.api.abankservice.entity.request.ReqBankCard;
import com.bank.api.abankservice.entity.response.RespBankCard;
import com.bank.api.abankservice.entity.response.RespTransaction;
import com.bank.api.abankservice.main.response.Response;
import com.bank.api.abankservice.service.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class BankCardController {

    private final BankCardService service;

    @PostMapping("/bankcard/get")
    public Response<RespBankCard> getBankCard(@RequestBody ReqBankCard reqBankCard) {
        return service.getCard(reqBankCard);
    }

    @PutMapping("bankcard/pay")
    public Response<RespTransaction> payAmount(@RequestBody ReqBankCard reqBankCard) {
        return service.payToCard(reqBankCard);
    }

}
