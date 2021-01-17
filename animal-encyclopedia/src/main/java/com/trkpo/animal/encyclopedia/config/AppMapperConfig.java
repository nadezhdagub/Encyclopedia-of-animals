/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.config;

import org.mapstruct.MapperConfig;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;
import static org.mapstruct.ReportingPolicy.WARN;

@MapperConfig(componentModel = "spring",
        unmappedTargetPolicy = WARN,
        collectionMappingStrategy = ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = IGNORE
)
public interface AppMapperConfig {
}
