package com.trkpo.animal.encyclopedia.usecase.type;

import com.trkpo.animal.encyclopedia.api.model.type.TypeDto;
import com.trkpo.animal.encyclopedia.entity.Type;
import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.repository.TypeRepository;
import com.trkpo.animal.encyclopedia.usecase.type.mapper.TypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateTypeUsecase {
    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;

    public Type execute(UUID typeId, TypeDto typeDto) {
        return typeRepository.findById(typeId)
                .map(dbCompany -> {
                    typeMapper.updateTypeFromDto(typeDto, dbCompany);
                    return typeRepository.save(dbCompany);
                })
                .orElseThrow(() -> new NotFoundException(
                        String.format("type(%s) not found", typeId.toString())));
    }
}
