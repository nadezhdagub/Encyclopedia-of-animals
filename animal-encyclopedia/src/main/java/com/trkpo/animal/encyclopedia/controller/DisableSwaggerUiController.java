/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Profile("!swagger")
@RestController
@Slf4j
public class DisableSwaggerUiController {
    @GetMapping(value = "/swagger-ui.html")
    public ResponseEntity<Void> getSwagger(HttpServletResponse httpResponse) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
