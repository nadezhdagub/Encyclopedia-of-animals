package com.trkpo.animal.encyclopedia.usecase.account.mapper;

import com.trkpo.animal.encyclopedia.api.model.account.AccountDto;
import com.trkpo.animal.encyclopedia.api.model.account.AccountDto.AccountDtoBuilder;
import com.trkpo.animal.encyclopedia.api.model.account.UpdateAccountDto;
import com.trkpo.animal.encyclopedia.entity.Account;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T07:35:15+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9.1 (Ubuntu)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account forUpdateDto(UpdateAccountDto dto, Account entity) {
        if ( dto == null ) {
            return null;
        }

        if ( dto.getAccessLevel() != null ) {
            entity.setAccessLevel( accessLevelFromString( dto.getAccessLevel() ) );
        }
        if ( dto.getParent() != null ) {
            entity.setParent( dto.getParent() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }

        return entity;
    }

    @Override
    public AccountDto toDto(Account entity) {
        if ( entity == null ) {
            return null;
        }

        AccountDtoBuilder accountDto = AccountDto.builder();

        accountDto.accessLevel( accessLevelToString( entity.getAccessLevel() ) );
        accountDto.id( entity.getId() );
        accountDto.parent( entity.getParent() );
        accountDto.name( entity.getName() );
        accountDto.email( entity.getEmail() );

        return accountDto.build();
    }
}
