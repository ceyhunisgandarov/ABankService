package com.bank.api.abankservice.security.service;

import com.bank.api.abankservice.main.exception.ExceptionConstant;
import com.bank.api.abankservice.main.exception.PaymentException;
import com.bank.api.abankservice.main.response.Response;
import com.bank.api.abankservice.main.response.ResponseStatus;
import com.bank.api.abankservice.security.UserRepository;
import com.bank.api.abankservice.security.model.ReqAuth;
import com.bank.api.abankservice.security.model.RespAuth;
import com.bank.api.abankservice.security.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final JwtService jwtService;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    @Override
    public Response<RespAuth> login(ReqAuth request) {
        Response<RespAuth> response = new Response<>();

        try {
            String userName = request.getUserName();
            String password = request.getPassword();
            System.out.println(userName + ", " + password);
            if (userName==null && password==null) {
                throw new PaymentException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUserName(),
                    request.getPassword()
            ));
            User user = repository.findUserByUserName(userName);
            System.out.println(user.getUsername());
            if (user==null) {
                throw new PaymentException(ExceptionConstant.USER_NOT_FOUND, "User not found");
            }
            var jwtToken = jwtService.generateToken(user);
            System.out.println(jwtToken);
            RespAuth respAuth = RespAuth.builder()
                    .token(jwtToken)
                    .build();
            response.setT(respAuth);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (PaymentException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
            ex.getMessage();
        }

        return response;
    }

}
