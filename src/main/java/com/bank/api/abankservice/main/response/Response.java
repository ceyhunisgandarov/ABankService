package com.bank.api.abankservice.main.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response <T> {

    @JsonProperty(value = "response")
    private T t;
    private ResponseStatus status;

}
