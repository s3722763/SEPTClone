package com.rmit.majorproject.BackEnd.Repositories;

import com.rmit.majorproject.BackEnd.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {

    @Override
    Iterable<Account> findAllById (Iterable<Long> iterable);
}