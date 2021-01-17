/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.security.jwt;

import com.trkpo.animal.encyclopedia.security.MzUserDetails;
import com.trkpo.animal.encyclopedia.security.service.JwtAuthorization;
import com.trkpo.animal.encyclopedia.security.service.SecretService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

    public static final String TOKEN_PREFIX = "Token ";
    private static final int TOKEN_PREFIX_LENGTH = TOKEN_PREFIX.length();

    private final SecretService secretService;
    private final JwtAuthorization jwtAuthorization;

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = resolveToken(request);
        if (!StringUtils.hasText(token)) {
            return null;
        }

        try {
            // check the token sign and get the claims
            final Claims claims = Jwts.parser()
                    .setSigningKeyResolver(secretService.getSigningKeyResolver())
                    .parseClaimsJws(token).getBody();
            // authorise
            final MzUserDetails userDetails = jwtAuthorization.authorise(claims);
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch (Exception e) {
            log.info("Authorization unsuccessfull: {}", e.getMessage());
        }
        return null;
    }

    public String resolveToken(HttpServletRequest req) {
        final String authHeaderValue = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeaderValue != null && authHeaderValue.startsWith(TOKEN_PREFIX)) {
            return authHeaderValue.substring(TOKEN_PREFIX_LENGTH);
        }
        return null;
    }

}
