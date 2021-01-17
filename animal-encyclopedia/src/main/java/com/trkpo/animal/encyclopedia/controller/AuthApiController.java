/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.controller;

import com.trkpo.animal.encyclopedia.api.AuthApi;
import com.trkpo.animal.encyclopedia.api.model.auth.AuthModel;
import com.trkpo.animal.encyclopedia.api.model.auth.AuthResult;
import com.trkpo.animal.encyclopedia.exceptions.NotAuthorizedException;
import com.trkpo.animal.encyclopedia.security.SecurityHelper;
import com.trkpo.animal.encyclopedia.usecase.auth.LoginUsecase;
import com.trkpo.animal.encyclopedia.usecase.auth.LogoutUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthApiController implements AuthApi {

    private final LoginUsecase loginUsecase;
    private final LogoutUsecase logoutUsecase;

    @Override
    public ResponseEntity<AuthResult> login(@Valid AuthModel body) {
        log.debug("AuthModel: " + body);
        try {
            return ResponseEntity.accepted().body(loginUsecase.execute(body));
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            log.debug("Login failed.", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> logout() {
        logoutUsecase.execute(SecurityHelper.currentAccount()
                .orElseThrow(() -> new NotAuthorizedException("Not logged in.")));
        return ResponseEntity.noContent().build();
    }
}
