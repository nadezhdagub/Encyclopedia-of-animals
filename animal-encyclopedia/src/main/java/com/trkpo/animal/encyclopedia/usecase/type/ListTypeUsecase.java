package com.trkpo.animal.encyclopedia.usecase.type;

import com.trkpo.animal.encyclopedia.entity.Type;
import com.trkpo.animal.encyclopedia.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTypeUsecase {
    private final TypeRepository typeRepository;

    public List<Type> execute() {
        return typeRepository.findAll();
    }
}
