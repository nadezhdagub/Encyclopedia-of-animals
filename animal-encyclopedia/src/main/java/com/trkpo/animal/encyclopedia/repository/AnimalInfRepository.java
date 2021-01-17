package com.trkpo.animal.encyclopedia.repository;

import com.trkpo.animal.encyclopedia.entity.AnimalInf;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AnimalInfRepository extends JpaRepository<AnimalInf, UUID> {
    Optional<AnimalInf> findById(@NonNull UUID id);
}
