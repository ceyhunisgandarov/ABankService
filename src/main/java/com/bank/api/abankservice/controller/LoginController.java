package com.bank.api.abankservice.controller;

import com.bank.api.abankservice.entity.Customer;
import com.bank.api.abankservice.main.response.Response;
import com.bank.api.abankservice.security.model.ReqAuth;
import com.bank.api.abankservice.security.model.RespAuth;
import com.bank.api.abankservice.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping("/login")
    public Response<RespAuth> login(@RequestBody ReqAuth request) throws Exception {
        return authService.login(request);
    }

}
