/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.api.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthModel {
    private String username;
    private String password;
    private String phone;
}
