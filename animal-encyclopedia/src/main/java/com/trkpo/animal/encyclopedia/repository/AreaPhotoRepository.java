package com.trkpo.animal.encyclopedia.repository;

import com.trkpo.animal.encyclopedia.entity.AreaPhoto;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AreaPhotoRepository   extends JpaRepository<AreaPhoto, UUID> {
    Optional<AreaPhoto> findById(@NonNull UUID id);
}
