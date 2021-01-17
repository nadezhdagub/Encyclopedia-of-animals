package com.trkpo.animal.encyclopedia.usecase.area_photo;

import com.trkpo.animal.encyclopedia.entity.AreaPhoto;
import com.trkpo.animal.encyclopedia.repository.AreaPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListAreaPhotoUsecase {
    private final AreaPhotoRepository areaPhotoRepository;

    public List<AreaPhoto> execute() {
        return areaPhotoRepository.findAll();
    }
}
