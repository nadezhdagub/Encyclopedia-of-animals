/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.controller;

import com.trkpo.animal.encyclopedia.api.AccountApi;
import com.trkpo.animal.encyclopedia.api.model.account.AccountDto;
import com.trkpo.animal.encyclopedia.api.model.account.CreateAccountDto;
import com.trkpo.animal.encyclopedia.api.model.account.UpdateAccountDto;
import com.trkpo.animal.encyclopedia.security.SecurityHelper;
import com.trkpo.animal.encyclopedia.usecase.account.CreateAccountUsecase;
import com.trkpo.animal.encyclopedia.usecase.account.DeleteAccountUseCase;
import com.trkpo.animal.encyclopedia.usecase.account.ListAccountUsecase;
import com.trkpo.animal.encyclopedia.usecase.account.UpdateAccountUsecase;
import com.trkpo.animal.encyclopedia.usecase.account.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class AccountApiController implements AccountApi {

    private final ListAccountUsecase listAccountUsecase;
    private final CreateAccountUsecase createAccountUsecase;
    private final UpdateAccountUsecase updateAccountUsecase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final AccountMapper accountMapper;

    @Override
    public ResponseEntity<List<AccountDto>> list() {
        return ResponseEntity.ok(listAccountUsecase.execute().stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<AccountDto> create(CreateAccountDto dto) {
        Assert.notNull(dto, "Body should be present.");
        Assert.hasText(dto.getEmail(), "Email should be present.");
        return Optional.of(dto)
                .map(createAccountUsecase::execute)
                .map(e -> {
                    log.info("Account successfully created: {}", e);
                    return e;
                })
                .map(accountMapper::toDto)
                .map(returnDto -> ResponseEntity.status(HttpStatus.CREATED).body(returnDto))
                .orElse(ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<AccountDto> update(UUID accountId, UpdateAccountDto dto) {
        return SecurityHelper.currentAccount()
                .map(ignored -> updateAccountUsecase.execute(accountId, dto))
                .map(e -> {
                    log.info("Account {} successfully updated.", accountId);
                    return e;
                })
                .map(accountMapper::toDto)
                .map(returnDto -> ResponseEntity.status(HttpStatus.CREATED).body(returnDto))
                .orElse(ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<Void> delete(UUID accountId) {
        deleteAccountUseCase.execute(accountId);
        log.info("Account {} successfully deleted.", accountId);
        return ResponseEntity.noContent().build();
    }
}
