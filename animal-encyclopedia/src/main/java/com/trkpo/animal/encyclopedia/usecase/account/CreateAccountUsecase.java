
package com.trkpo.animal.encyclopedia.usecase.account;

import com.trkpo.animal.encyclopedia.api.model.account.CreateAccountDto;
import com.trkpo.animal.encyclopedia.entity.Account;
import com.trkpo.animal.encyclopedia.repository.AccountRepository;
import com.trkpo.animal.encyclopedia.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

import static com.trkpo.animal.encyclopedia.security.SecurityRole.getALFromString;

@Service
@RequiredArgsConstructor
@Validated
public class CreateAccountUsecase {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    public Account execute(CreateAccountDto dto) {
        Assert.notNull(dto, "Empty request not allowable.");
        ValidationUtils.validateEmail(dto.getEmail());
        ValidationUtils.validatePassword(dto.getPassword());

        return Optional.of(dto)
                .map(d -> accountRepository.save(Account.builder()
                        .id(UUID.randomUUID())
                        .email(dto.getEmail().trim().toLowerCase())
                        .name(dto.getName())
                        .passwd(passwordEncoder.encode(dto.getPassword()))
                        .parent(dto.getParent())
                        .accessLevel(getALFromString(dto.getAccessLevel()))
                        .build()))
                .get();
    }
}
