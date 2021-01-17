package com.trkpo.animal.encyclopedia.repository;

import com.trkpo.animal.encyclopedia.entity.Type;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TypeRepository extends JpaRepository<Type, UUID> {
    Optional<Type> findById(@NonNull UUID id);
}
