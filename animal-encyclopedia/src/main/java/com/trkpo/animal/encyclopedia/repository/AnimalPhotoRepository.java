package com.trkpo.animal.encyclopedia.repository;

import com.trkpo.animal.encyclopedia.entity.AnimalPhoto;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AnimalPhotoRepository extends JpaRepository<AnimalPhoto, UUID> {
    Optional<AnimalPhoto> findById(@NonNull UUID id);
}
