/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.usecase.auth;

import com.trkpo.animal.encyclopedia.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogoutUsecase {
    private final AccountRepository accountRepository;

    public void execute(UUID aid) {
        Optional.ofNullable(aid)
                .flatMap(accountRepository::findById)
                .map(accountRepository::save)
                .ifPresent(e -> log.info("Account {} logged out.", e.getId()));
    }
}
