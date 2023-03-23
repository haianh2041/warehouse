package com.example.warehouse.repository;

import com.example.warehouse.entity.Account;
import com.example.warehouse.model.dto.AccountDTO;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepository {
    void login(String username, String password);

    List<AccountDTO> getAccountDTO();

    Short getAccountStatus(String user);
}
