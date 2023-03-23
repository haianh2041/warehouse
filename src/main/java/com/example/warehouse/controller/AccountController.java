package com.example.warehouse.controller;

import com.example.warehouse.model.dto.AccountDTO;
import com.example.warehouse.repository.AccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    AccountRepositoryImpl accountRepository;

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public void Login(@RequestBody AccountDTO accountDTO) {
        accountRepository.login(accountDTO.getUsername(),accountDTO.getPassword());
    }

    @GetMapping(value = "getAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccountDTO> getAccountDTO(){
        return accountRepository.getAccountDTO();
    }

    @GetMapping(value = "getAccountStatus/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Short getAccountStatus(@PathVariable String user){
        return accountRepository.getAccountStatus(user);
    }
}
