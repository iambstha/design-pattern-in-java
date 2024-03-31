package com.bishal.factorydesign.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.bishal.factorydesign.dto.StudentDTO;
import com.bishal.factorydesign.entity.Student;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {

	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

	Student toEntity(StudentDTO studentDto);

	StudentDTO toDTO(Student student);

	void updateStudentFromDto(StudentDTO studentDto, @MappingTarget Student student);

}
