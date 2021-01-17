package com.trkpo.animal.encyclopedia.api.model.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeDto {
    private UUID id;
    private String name;
    private UUID animal_id;

}
