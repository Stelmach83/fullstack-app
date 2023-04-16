package eu.stelmaszak.fullstackapp.demo;

import eu.stelmaszak.fullstackapp.gen.model.CreateDemoRequestDto;
import eu.stelmaszak.fullstackapp.gen.model.CreatedDemoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DemoMapper {

    @Mapping(target = "id", ignore = true)
    DemoEntity mapToEntity(CreateDemoRequestDto requestDto);

    CreatedDemoResponseDto mapToCreatedDemoResponseDto(DemoEntity demoEntity);
}
