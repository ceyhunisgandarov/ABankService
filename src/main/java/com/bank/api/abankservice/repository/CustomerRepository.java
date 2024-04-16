package com.bank.api.abankservice.repository;

import com.bank.api.abankservice.entity.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByDobAndActive(Date customerDob, int value);

    @Override
    List<Customer> findAll();


}
