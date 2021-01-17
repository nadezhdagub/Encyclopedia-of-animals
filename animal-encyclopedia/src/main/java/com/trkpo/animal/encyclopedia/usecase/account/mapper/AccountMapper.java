/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.usecase.account.mapper;

import com.trkpo.animal.encyclopedia.api.model.account.AccountDto;
import com.trkpo.animal.encyclopedia.api.model.account.UpdateAccountDto;
import com.trkpo.animal.encyclopedia.config.AppMapperConfig;
import com.trkpo.animal.encyclopedia.entity.Account;
import com.trkpo.animal.encyclopedia.security.SecurityRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.stream.Collectors;

@Mapper(config = AppMapperConfig.class)
public interface AccountMapper {
    @Mapping(target = "passwd", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accessLevel", source = "accessLevel",
            qualifiedByName = "fromString")
    Account forUpdateDto(UpdateAccountDto dto, @MappingTarget Account entity);

    @Named("fromString")
    default Long accessLevelFromString(String roles) {
        if (roles == null) {
            return 0L;
        }
        return SecurityRole.getALFromString(roles);
    }

    @Mapping(target = "accessLevel", source = "accessLevel",
            qualifiedByName = "toString")
    AccountDto toDto(Account entity);

    @Named("toString")
    default String accessLevelToString(Long accessLevel) {
        if (accessLevel == null) {
            return null;
        }
        return SecurityRole.getRolesByAL(accessLevel).stream()
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
