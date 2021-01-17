/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.api;

import com.trkpo.animal.encyclopedia.api.model.auth.AuthModel;
import com.trkpo.animal.encyclopedia.api.model.auth.AuthResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "Auth API", tags = "Auth API")
public interface AuthApi {
    @PostMapping("/auth/login")
    @ApiOperation(value = "Получить токен",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    ResponseEntity<AuthResult> login(@Valid @RequestBody AuthModel body);

    @PostMapping("/auth/logout")
    @ApiOperation(value = "Log out",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content")
    })
    ResponseEntity<Void> logout();
}
