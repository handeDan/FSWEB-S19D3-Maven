package com.workintech.s19d2.dto;

import com.workintech.s19d2.entity.Account;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {

    @Autowired
    private EntityManager entityManager;

    public Account findById(Long id) {
        return entityManager.find(Account.class, id);
    }

    public void save(Account account) {
        entityManager.persist(account);
    }

    public void update(Account account) {
        entityManager.merge(account);
    }

    public void delete(Long id) {
        Account account = findById(id);
        if (account != null) {
            entityManager.remove(account);
        }
    }
}
