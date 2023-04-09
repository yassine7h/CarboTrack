package com.yassine7h.parcauto.repositories;

import com.yassine7h.parcauto.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Optional<Account> findByEmail(String email);
}
