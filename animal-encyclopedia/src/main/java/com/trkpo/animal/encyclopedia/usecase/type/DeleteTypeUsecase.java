package com.trkpo.animal.encyclopedia.usecase.type;

import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteTypeUsecase {
    private final TypeRepository typeRepository;

    public void execute(UUID typeId) {
        typeRepository.findById(typeId)
                .map(type -> {
                    typeRepository.delete(type);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("type(%s) not found", typeId.toString())));
    }
}
