
package com.trkpo.animal.encyclopedia.usecase.auth;

import com.trkpo.animal.encyclopedia.api.model.auth.AuthModel;
import com.trkpo.animal.encyclopedia.api.model.auth.AuthResult;
import com.trkpo.animal.encyclopedia.entity.Account;
import com.trkpo.animal.encyclopedia.repository.AccountRepository;
import com.trkpo.animal.encyclopedia.security.service.JwtCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginUsecase {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtCreator jwtCreator;

    public AuthResult execute(@NotNull AuthModel request) {
        Assert.hasText(request.getPassword(), "Password should be supplied. Request: " + request);

        // TODO: password length should not be less than 4 symbols.

        Account account;
        if (!StringUtils.isEmpty(request.getUsername())) {
            account = accountRepository.findByEmail(request.getUsername().trim().toLowerCase());

        } else {
            throw new IllegalArgumentException(
                    "Email or phone should be supplied. Request: " + request);
        }

        if (account == null) {
            throw new IllegalStateException("Account not found. Request: " + request);
        }


        if (!passwordEncoder.matches(request.getPassword(), account.getPasswd())) {
            throw new IllegalStateException("Wrong password. aid: " + account.getId());
        }

        final AuthResult authResult = new AuthResult(jwtCreator.create(account));
        log.info("Account {} logged in.", account.getId());
        return authResult;
    }
}
