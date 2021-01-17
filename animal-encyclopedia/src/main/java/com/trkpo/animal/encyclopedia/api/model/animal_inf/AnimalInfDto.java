package com.trkpo.animal.encyclopedia.api.model.animal_inf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalInfDto {
    private UUID id;
    private String inf_of_type;
    private String inf_of_area;
    private String inf_of_number;
    private String inf_of_downsizing;
    private String inf_of_security;
}
