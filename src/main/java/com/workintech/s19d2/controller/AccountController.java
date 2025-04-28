package com.workintech.s19d2.controller;

import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.service.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  @Autowired private AccountService accountService;

  @GetMapping("/")
  public ResponseEntity<List<Account>> getAllAccounts() {
    return ResponseEntity.ok(accountService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
    return ResponseEntity.ok(accountService.find(id));
  }

  @PostMapping
  public ResponseEntity<Account> createAccount(@RequestBody Account account) {
    return ResponseEntity.ok(accountService.save(account));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
    return ResponseEntity.ok(accountService.update(id, account));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
    accountService.delete(id);
    return ResponseEntity.ok("Account deleted successfully.");
  }
}
