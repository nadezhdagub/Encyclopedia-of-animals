/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.usecase.account;

import com.trkpo.animal.encyclopedia.api.model.account.UpdateAccountDto;
import com.trkpo.animal.encyclopedia.entity.Account;
import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.repository.AccountRepository;
import com.trkpo.animal.encyclopedia.usecase.account.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateAccountUsecase {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public Account execute(UUID accountId, UpdateAccountDto updateAccountDto) {
        return accountRepository.findById(accountId)
                .map(account -> accountRepository.save(
                        accountMapper.forUpdateDto(updateAccountDto, account)))
                .orElseThrow(() -> new NotFoundException(
                        String.format("Account(%s) not found", accountId.toString())));
    }
}
