/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.usecase.account;

import com.trkpo.animal.encyclopedia.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAccountUseCase {
    private final AccountRepository accountRepository;

    public void execute(UUID accountId) {
        accountRepository.deleteById(accountId);
    }
}
