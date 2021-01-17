package com.trkpo.animal.encyclopedia.usecase.type.mapper;

import com.trkpo.animal.encyclopedia.api.model.type.TypeDto;
import com.trkpo.animal.encyclopedia.api.model.type.TypeDto.TypeDtoBuilder;
import com.trkpo.animal.encyclopedia.entity.Type;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T07:35:14+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9.1 (Ubuntu)"
)
@Component
public class TypeMapperImpl implements TypeMapper {

    @Override
    public TypeDto toDto(Type type) {
        if ( type == null ) {
            return null;
        }

        TypeDtoBuilder typeDto = TypeDto.builder();

        typeDto.id( type.getId() );
        typeDto.name( type.getName() );
        typeDto.animal_id( type.getAnimal_id() );

        return typeDto.build();
    }

    @Override
    public void updateTypeFromDto(TypeDto typeDto, Type type) {
        if ( typeDto == null ) {
            return;
        }

        if ( typeDto.getId() != null ) {
            type.setId( typeDto.getId() );
        }
        if ( typeDto.getAnimal_id() != null ) {
            type.setAnimal_id( typeDto.getAnimal_id() );
        }
        if ( typeDto.getName() != null ) {
            type.setName( typeDto.getName() );
        }
    }
}
