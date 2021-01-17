package com.trkpo.animal.encyclopedia.usecase.area_photo;

import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.repository.AreaPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAreaPhotoUsecase {
    private final AreaPhotoRepository areaPhotoRepository;

    public void execute(UUID typeId) {
        areaPhotoRepository.findById(typeId)
                .map(type -> {
                    areaPhotoRepository.delete(type);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("photo of area(%s) not found", typeId.toString())));
    }
}
