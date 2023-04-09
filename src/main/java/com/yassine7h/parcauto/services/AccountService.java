package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.models.Account;
import com.yassine7h.parcauto.repositories.AccountRepository;
import com.yassine7h.parcauto.services.interfaces.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(int id) {
        return accountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,Account.class));
    }
    @Override
    public Account getByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException(email,Account.class));
    }

    @Override
    public int add(Account account) {
        Optional<Account> accountOptional=accountRepository.findById(account.getId());
        if(accountOptional.isPresent()) throw new ResourceExistException(account.getId(),Account.class);
        Account addedAccount=accountRepository.save(account);
        return addedAccount.getId();
    }

    @Override
    public void update(Account account) {
        Optional<Account> accountOptional=accountRepository.findById(account.getId());
        if(!accountOptional.isPresent()) throw new ResourceNotFoundException(account.getId(),Account.class);
        accountRepository.save(account);
    }

    @Override
    public void delete(int id) {
        Optional<Account> accountOptional=accountRepository.findById(id);
        if(!accountOptional.isPresent()) throw new ResourceNotFoundException(id,Account.class);
        accountRepository.deleteById(id);
    }
}
