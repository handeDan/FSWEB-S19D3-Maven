package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.repository.AccountRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

  @Autowired private AccountRepository accountRepository;

  @Override
  public List<Account> findAll() {
    return accountRepository.findAll();
  }

  @Override
  public Account find(Long id) {
    return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
  }

  @Override
  public Account save(Account account) {
    return accountRepository.save(account);
  }

  @Override
  public Account update(Long id, Account account) {
    Account existingAccount = find(id);
    existingAccount.setName(account.getName());
    return accountRepository.save(existingAccount);
  }

  @Override
  public void delete(Long id) {
    accountRepository.deleteById(id);
  }
}
