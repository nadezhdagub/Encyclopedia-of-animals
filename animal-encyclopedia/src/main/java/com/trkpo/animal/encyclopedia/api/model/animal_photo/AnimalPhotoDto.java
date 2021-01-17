package com.trkpo.animal.encyclopedia.api.model.animal_photo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalPhotoDto {
    private UUID id;
    private String animal_photo;
    private UUID animal_inf_id;
}
