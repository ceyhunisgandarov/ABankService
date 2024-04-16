package com.bank.api.abankservice.repository;

import com.bank.api.abankservice.entity.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepository extends JpaRepository<BankCard, Long> {

    BankCard findBankCardByCardNumberAndActive(String cardNumber, int active);

}
