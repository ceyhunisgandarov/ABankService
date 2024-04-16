package com.bank.api.abankservice.controller;

import com.bank.api.abankservice.main.response.Response;
import com.bank.api.abankservice.main.response.ResponseStatus;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured_area")
public class BankController {

    @GetMapping("/field")
    public ResponseEntity<String> secured (){
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

}
