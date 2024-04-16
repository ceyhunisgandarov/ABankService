package com.bank.api.abankservice.repository;

import com.bank.api.abankservice.entity.Account;
import com.bank.api.abankservice.entity.Credit;
import com.bank.api.abankservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    Credit findCreditByAgreementNumberAndAccountAndActive(String agreementNumber, Account account, int active);

}
