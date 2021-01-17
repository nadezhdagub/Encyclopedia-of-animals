
package com.trkpo.animal.encyclopedia.usecase.account;

import com.trkpo.animal.encyclopedia.entity.Account;
import com.trkpo.animal.encyclopedia.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListAccountUsecase {
    private final AccountRepository accountRepository;

    public List<Account> execute() {

        return accountRepository.findAll();
    }
}
