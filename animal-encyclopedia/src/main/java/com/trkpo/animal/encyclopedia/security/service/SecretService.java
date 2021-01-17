/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.security.service;

import com.trkpo.animal.encyclopedia.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.impl.TextCodec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecretService {

    private final JwtConfig.AppJwt jwtConfig;

    private SigningKeyResolver signingKeyResolver = new SigningKeyResolverAdapter() {
        @Override
        public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
            return TextCodec.BASE64.decode(jwtConfig.getSecrets().get(header.getAlgorithm()));
        }
    };

    public SigningKeyResolver getSigningKeyResolver() {
        return signingKeyResolver;
    }
}