package com.trkpo.animal.encyclopedia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "animalInf")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalInf {
    @Id
    private UUID id;
    private String inf_of_type;
    private String inf_of_area;
    private String inf_of_number;
    private String inf_of_downsizing;
    private String inf_of_security;

}
