package com.bank.api.abankservice.service.impl;

import com.bank.api.abankservice.entity.Account;
import com.bank.api.abankservice.entity.BankCard;
import com.bank.api.abankservice.entity.Transaction;
import com.bank.api.abankservice.entity.request.ReqBankCard;
import com.bank.api.abankservice.entity.response.RespBankCard;
import com.bank.api.abankservice.entity.response.RespTransaction;
import com.bank.api.abankservice.main.enums.EnumAvailableStatus;
import com.bank.api.abankservice.main.exception.ExceptionConstant;
import com.bank.api.abankservice.main.exception.PaymentException;
import com.bank.api.abankservice.main.response.Response;
import com.bank.api.abankservice.main.response.ResponseStatus;
import com.bank.api.abankservice.repository.AccountRepository;
import com.bank.api.abankservice.repository.BankCardRepository;
import com.bank.api.abankservice.repository.CustomerRepository;
import com.bank.api.abankservice.repository.TransactionRepository;
import com.bank.api.abankservice.service.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class BankCardServiceImpl implements BankCardService {

    private final BankCardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Response<RespBankCard> getCard(ReqBankCard reqBankCard) {
        Response<RespBankCard> response = new Response<>();

        try {
            String cardNumber = reqBankCard.getBankCardNumber();
            if (cardNumber == null) {
                throw new PaymentException(ExceptionConstant.INVALID_REQUEST_DATA, "INVALID DATA");
            }
            BankCard bankCard = cardRepository.findBankCardByCardNumberAndActive(cardNumber, EnumAvailableStatus.ACTIVE.value);
            if (bankCard == null) {
                throw new PaymentException(ExceptionConstant.CARD_NOT_FOUND, "Card not found");
            }
            RespBankCard respBankCard = RespBankCard.builder()
                    .customerName(bankCard.getAccount().getCustomer().getName())
                    .customerSurname(bankCard.getAccount().getCustomer().getSurname())
                    .accountNumber(bankCard.getAccount().getAccountNo())
                    .currency(bankCard.getAccount().getCurrency())
                    .maxLimit(bankCard.getMaxLimit())
                    .cardNumber(bankCard.getCardNumber())
                    .build();
            response.setT(respBankCard);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (PaymentException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return response;
    }

    @Override
    public Response<RespTransaction> payToCard(ReqBankCard reqBankCard) {
        Response<RespTransaction> response = new Response();

        try {
            String cardNumber = reqBankCard.getBankCardNumber();
            BigDecimal payAmount = reqBankCard.getAmountOfPayment();
            String senderAccountNumber = reqBankCard.getSenderAccountNumber();
            if (cardNumber==null && (payAmount==null && payAmount.compareTo(BigDecimal.ZERO)!=1) && senderAccountNumber==null) {
                throw new PaymentException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            BankCard card = cardRepository.findBankCardByCardNumberAndActive(cardNumber, EnumAvailableStatus.ACTIVE.value);
            if (card==null) {
                throw new PaymentException(ExceptionConstant.CARD_NOT_FOUND, "Card not found");
            }
            Transaction transaction = Transaction.builder()
                    .transactionDate(new Date())
                    .amount(reqBankCard.getAmountOfPayment())
                    .account(card.getAccount())
                    .receiverAccountNumber(card.getAccount().getAccountNo())
                    .senderAccountNumber(senderAccountNumber)
                    .build();
            transaction = transactionRepository.save(transaction);
            String transactionId = "#" + (100000+transaction.getId());
            transaction.setTransactionId(transactionId);
            transaction = transactionRepository.save(transaction);
            BigDecimal balance = card.getCardBalance();
            card.setCardBalance(balance.add(payAmount));
            RespTransaction respTransaction = RespTransaction.builder()
                    .transactionId(transaction.getTransactionId())
                    .cardNumber(card.getCardNumber())
                    .customerFullName(card.getAccount().getCustomer().getName() + " " + card.getAccount().getCustomer().getSurname())
                    .amount(transaction.getAmount())
                    .currency(card.getAccount().getCurrency())
                    .build();
            cardRepository.save(card);
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
