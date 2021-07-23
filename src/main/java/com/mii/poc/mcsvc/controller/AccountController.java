package com.mii.poc.mcsvc.controller;

import com.mii.poc.mcsvc.RestResponse;
import com.mii.poc.mcsvc.dao.AccountRepository;
import com.mii.poc.mcsvc.domain.Account;
import com.mii.poc.mcsvc.util.DataNotFoundException;
import com.mii.poc.mcsvc.util.DuplicateException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ErwinSn
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(path = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse<List<Account>>> getAll() {
        List<Account> accounts = accountRepository.findAll();
        return ResponseEntity.ok().body(RestResponse.success(accounts));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse<Account>> get(@PathVariable Long id) {
        Account acc = accountRepository.findById(id)
                .map(account -> {
                    return account;
                })
                .orElseThrow(() -> new DataNotFoundException(id));
        return ResponseEntity.ok().body(RestResponse.success(acc));
    }

    @PostMapping(path = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse<Account>> save(@RequestBody Account account) {
        Account existingAccount = accountRepository.findByAccountNumber(account.getAccountNumber());
        if (existingAccount != null) {
            throw new DuplicateException(account.getAccountNumber());
        }
        LocalDateTime nowDateTime = LocalDateTime.now();
        account.setUpdateAt(nowDateTime);
        account.setCreatedAt(nowDateTime);
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.ok().body(RestResponse.success(savedAccount));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse<Account>> update(@RequestBody Account newAccount, @PathVariable Long id) {
        Account acc = accountRepository.findById(id)
                .map(account -> {
                    account.setAccountName(newAccount.getAccountName());
                    account.setAccountNumber(newAccount.getAccountNumber());
                    account.setCif(newAccount.getCif());
                    account.setUpdateAt(LocalDateTime.now());
                    return account;
                })
                .orElseThrow(() -> new DataNotFoundException(id));
        accountRepository.save(acc);
        return ResponseEntity.ok().body(RestResponse.success(acc));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RestResponse<Account>> delete(@PathVariable Long id) {
        Account acc = accountRepository.findById(id)
                .map(account -> {
                    return account;
                })
                .orElseThrow(() -> new DataNotFoundException(id));
        accountRepository.delete(acc);
        return ResponseEntity.ok().body(RestResponse.success("Delete Successfully", acc));
    }
    
    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse<List<Account>>> search(@RequestParam String keywords) {
        List<Account> accounts = accountRepository.search(keywords);
        return ResponseEntity.ok().body(RestResponse.success(accounts));
    }

}
