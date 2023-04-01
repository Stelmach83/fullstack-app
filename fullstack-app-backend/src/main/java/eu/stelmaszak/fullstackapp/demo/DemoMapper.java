package eu.stelmaszak.fullstackapp.demo;

import eu.stelmaszak.fullstackapp.gen.model.CreateDemoRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DemoMapper {

    @Mapping(target = "id", ignore = true)
    DemoEntity mapToEntity(CreateDemoRequestDto requestDto);
}
