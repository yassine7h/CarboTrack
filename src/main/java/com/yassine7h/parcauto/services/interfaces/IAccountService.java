package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.models.Account;

import java.util.List;

public interface IAccountService {
    public List<Account> getAll();
    public Account getById(int id);
    Account getByEmail(String email);
    public int add(Account driver);
    public void update(Account driver);
    public void delete(int id);

}
