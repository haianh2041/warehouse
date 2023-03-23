package com.example.warehouse.repository;

import com.example.warehouse.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepositoryJPA extends JpaRepository<Account, Integer> {
    @Query("FROM Account a WHERE a.username like :username")
    Account getAccount(@Param("username") String username);
}
