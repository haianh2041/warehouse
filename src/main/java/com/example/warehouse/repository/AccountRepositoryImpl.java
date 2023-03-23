package com.example.warehouse.repository;

import com.example.warehouse.entity.Account;
import com.example.warehouse.entity.Product;
import com.example.warehouse.model.dto.AccountDTO;
import com.example.warehouse.model.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    AccountRepositoryJPA accountRepositoryJPA;
    @Autowired
    private final ProductMapper mapper;

    @Override
    public void login(String username, String password) {
        Account account = accountRepositoryJPA.getAccount(username);
        System.out.println(account.getUsername());
        System.out.println(account.getPassword());
        if (account.getPassword().equals(password)) {
            account.setStatus((short) 1);
            accountRepositoryJPA.save(account);
        }
    }

    @Override
    public List<AccountDTO> getAccountDTO() {
        List<Account> accounts = accountRepositoryJPA.findAll();
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for(Account account : accounts){
            AccountDTO accountDTO = mapper.modelMapper().map(account, AccountDTO.class);
            accountDTOS.add(accountDTO);
            System.out.println(accountDTO);
        }
        return accountDTOS;
    }

    @Override
    public Short getAccountStatus(String user) {
        List<Account> accounts = accountRepositoryJPA.findAll();
        for (Account account : accounts){
            if(account.getUsername().equals(user)){
                System.out.println(account.getStatus());
                return account.getStatus();
            }
        }
        return null;
    }
}
