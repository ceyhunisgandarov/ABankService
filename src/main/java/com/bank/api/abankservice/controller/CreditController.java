package com.bank.api.abankservice.controller;

import com.bank.api.abankservice.entity.request.ReqBankCard;
import com.bank.api.abankservice.entity.request.ReqCredit;
import com.bank.api.abankservice.entity.response.RespCredit;
import com.bank.api.abankservice.entity.response.RespTransaction;
import com.bank.api.abankservice.main.response.Response;
import com.bank.api.abankservice.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/credit/get")
    public Response<RespCredit> getCredit (@RequestBody ReqCredit reqCredit) {
        return creditService.getCredit(reqCredit);
    }

    @PutMapping("/credit/pay")
    public Response<RespTransaction> payAmount(@RequestBody ReqCredit reqCredit) {
        return creditService.payCredit(reqCredit);
    }


}
