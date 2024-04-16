package com.bank.api.abankservice.service.impl;

import com.bank.api.abankservice.entity.Account;
import com.bank.api.abankservice.entity.Credit;
import com.bank.api.abankservice.entity.Customer;
import com.bank.api.abankservice.entity.Transaction;
import com.bank.api.abankservice.entity.request.ReqCredit;
import com.bank.api.abankservice.entity.response.RespCredit;
import com.bank.api.abankservice.entity.response.RespTransaction;
import com.bank.api.abankservice.main.enums.EnumAvailableStatus;
import com.bank.api.abankservice.main.exception.ExceptionConstant;
import com.bank.api.abankservice.main.exception.PaymentException;
import com.bank.api.abankservice.main.response.Response;
import com.bank.api.abankservice.main.response.ResponseStatus;
import com.bank.api.abankservice.repository.AccountRepository;
import com.bank.api.abankservice.repository.CreditRepository;
import com.bank.api.abankservice.repository.CustomerRepository;
import com.bank.api.abankservice.repository.TransactionRepository;
import com.bank.api.abankservice.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Response<RespCredit> getCredit(ReqCredit reqCredit) {
        Response<RespCredit> response = new Response<>();

        try {
            String agreementNumber = reqCredit.getAgreementNumber();
            Date customerDob = reqCredit.getCustomerDob();
            if (agreementNumber == null && customerDob == null) {
                throw new PaymentException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            Customer customer = customerRepository.findCustomerByDobAndActive(customerDob, EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new PaymentException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found");
            }

            Account account = accountRepository.findAccountByCustomerAndActive(customer, EnumAvailableStatus.ACTIVE.value);
            if (account == null) {
                throw new PaymentException(ExceptionConstant.ACCOUNT_NOT_FOUND, "Account not found");
            }
            Credit credit = creditRepository.findCreditByAgreementNumberAndAccountAndActive(agreementNumber, account, EnumAvailableStatus.ACTIVE.value);
            if (credit == null) {
                throw new PaymentException(ExceptionConstant.CREDIT_NOT_FOUND, "Credit not found");
            }
            RespCredit respCredit = RespCredit.builder()
                    .agreementNumber(credit.getAgreementNumber())
                    .currency(credit.getAccount().getCurrency())
                    .customerFullName(credit.getAccount().getCustomer().getName() + " " + credit.getAccount().getCustomer().getSurname())
                    .creditAmount(credit.getCreditAmount())
                    .accountNumber(credit.getAccount().getAccountNo())
                    .remainingDept(credit.getRemainingDept())
                    .build();

            response.setT(respCredit);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (PaymentException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    @Override
    public Response<RespTransaction> payCredit(ReqCredit reqCredit) {
        Response<RespTransaction> response = new Response<>();

        try {
            String agreementNumber = reqCredit.getAgreementNumber();
            Date customerDob = reqCredit.getCustomerDob();
            BigDecimal payAmount = reqCredit.getPayAmount();
            String senderAccount = reqCredit.getSenderAccount();
            if (agreementNumber == null && customerDob == null && payAmount==null) {
                throw new PaymentException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            Customer customer = customerRepository.findCustomerByDobAndActive(customerDob, EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new PaymentException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            Account account = accountRepository.findAccountByCustomerAndActive(customer, EnumAvailableStatus.ACTIVE.value);
            if (account == null) {
                throw new PaymentException(ExceptionConstant.ACCOUNT_NOT_FOUND, "Account not found");
            }
            Credit credit = creditRepository.findCreditByAgreementNumberAndAccountAndActive(agreementNumber, account, EnumAvailableStatus.ACTIVE.value);
            if (credit == null) {
                throw new PaymentException(ExceptionConstant.CREDIT_NOT_FOUND, "Credit not found");
            }
            Transaction transaction = Transaction.builder()
                    .transactionDate(new Date())
                    .amount(reqCredit.getPayAmount())
                    .account(credit.getAccount())
                    .receiverAccountNumber(credit.getAccount().getAccountNo())
                    .senderAccountNumber(senderAccount)
                    .build();
            transaction = transactionRepository.save(transaction);
            String transactionId = "#" + (100000+transaction.getId());
            transaction.setTransactionId(transactionId);
            transaction = transactionRepository.save(transaction);
            BigDecimal remainingDept = credit.getRemainingDept();
            credit.setRemainingDept(remainingDept.subtract(payAmount));
            RespTransaction respTransaction = RespTransaction.builder()
                    .transactionId(transaction.getTransactionId())
                    .accountNumber(credit.getAccount().getAccountNo())
                    .customerFullName(credit.getAccount().getCustomer().getName() + " " + credit.getAccount().getCustomer().getSurname())
                    .agreementNumber(credit.getAgreementNumber())
                    .remainingDept(credit.getRemainingDept())
                    .amountOfCredit(credit.getCreditAmount())
                    .currency(credit.getAccount().getCurrency())
                    .build();
            creditRepository.save(credit);
            response.setT(respTransaction);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (PaymentException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }
}
