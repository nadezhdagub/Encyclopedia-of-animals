/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.config;

import com.trkpo.animal.encyclopedia.security.jwt.JwtAuthenticationEntryPoint;
import com.trkpo.animal.encyclopedia.security.jwt.JwtTokenAuthenticationFilter;
import com.trkpo.animal.encyclopedia.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final String[] WHITELIST = {
            "/auth/login",
            "/api-docs",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/favicon.ico",

    };

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(new AuthenticationProvider() {

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                log.info("AuthenticationProvider authenticate invoked.");
                return null;
            }

            @Override
            public boolean supports(Class<?> authentication) {
                log.info("AuthenticationProvider supports invoked.");
                return false;
            }
        });
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("SecurityLibAutoConfiguration applied.");

        http.httpBasic().disable();
        http.formLogin().disable();
        http.logout().disable();
        http.csrf().disable();

        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        http.addFilterBefore(new JwtTokenAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class);

        // don't create session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeRequests()
                 // .antMatchers("/**").permitAll() // remove it
                .antMatchers(WHITELIST).permitAll()
                .antMatchers(
                        "/accounts", "/accounts/**"
                ).hasRole("ROOT_ADMIN")
                .antMatchers("/**").hasRole("USER")
                .and().csrf().disable()
        ;
    }

}
