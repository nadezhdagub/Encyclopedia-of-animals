/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.security;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public enum SecurityRole {
    user(1),
    admin(1 << 1),
    root_admin(1 << 2),
    key_store(1 << 3),

    /**
     * TODO
     *      костыль
     *      роль необходимая для временной организации обмена данными с модулем обработки EGTS
     */
    fifth_leg(1 << 10);

    private long accessLevel;

    SecurityRole(long level) {
        accessLevel = level;
    }

    public long getAccessLevel() {
        return accessLevel;
    }

    public String getRoleString() {
        return "ROLE_" + name().toUpperCase();
    }

    public static List<SecurityRole> getRolesByAL(long al) {
        final List<SecurityRole> result = new ArrayList<>();
        for (SecurityRole role : values()) {
            if ((role.accessLevel & al) > 0) {
                result.add(role);
            }
        }
        return result;
    }

    /**
     * Converts a string like "user, admin" to inString value.
     * @param inString String like "user, admin"
     * @return Long inString value
     */
    public static long getALFromString(String inString) {
        if (StringUtils.isEmpty(inString)) {
            return 0L;
        }
        return Stream.of(inString.split(","))
                .map(String::trim)
                .map(SecurityRole::valueOf)
                .map(SecurityRole::getAccessLevel)
                .reduce(Long::sum)
                .orElse(0L);
    }
}
