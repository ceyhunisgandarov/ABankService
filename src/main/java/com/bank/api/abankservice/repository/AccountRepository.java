package com.bank.api.abankservice.repository;

import com.bank.api.abankservice.entity.Account;
import com.bank.api.abankservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByCustomerAndActive(Customer customer, int active);

}
