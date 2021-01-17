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
@Table(name="type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Type {
    @Id
    private UUID id;
    private UUID animal_id;
    private String name;
}
