/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.api;

import com.trkpo.animal.encyclopedia.api.model.account.AccountDto;
import com.trkpo.animal.encyclopedia.api.model.account.CreateAccountDto;
import com.trkpo.animal.encyclopedia.api.model.account.UpdateAccountDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "Account API", tags = "Account API")
@Validated
public interface AccountApi {

    @GetMapping("/accounts")
    @ApiOperation(value = "list accounts",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok")
    })
    ResponseEntity<List<AccountDto>> list();

    @PutMapping("/accounts")
    @ApiOperation(value = "Create account",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    ResponseEntity<AccountDto> create(@Valid @NotNull @RequestBody CreateAccountDto dto);

    @PutMapping("/accounts/{id}")
    @ApiOperation(value = "Update account",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    ResponseEntity<AccountDto> update(
            @Valid @NotNull @PathVariable("id") UUID accountId,
            @Valid @NotNull @RequestBody UpdateAccountDto updateAccountDto);

    @DeleteMapping("/accounts/{id}")
    @ApiOperation(value = "Remove account",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content")
    })
    ResponseEntity<Void> delete(@Valid @NotNull @PathVariable("id") UUID accountId);

}
