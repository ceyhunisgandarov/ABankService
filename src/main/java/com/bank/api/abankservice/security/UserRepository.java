package com.bank.api.abankservice.security;

import com.bank.api.abankservice.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserName(String username);

    Optional<User> findByUserName(String username);

}
