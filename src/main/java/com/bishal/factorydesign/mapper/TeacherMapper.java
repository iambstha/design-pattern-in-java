package com.bishal.factorydesign.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.bishal.factorydesign.dto.TeacherDTO;
import com.bishal.factorydesign.entity.Teacher;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface TeacherMapper {

	TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

	TeacherDTO toDTO(Teacher teacher);

	Teacher toEntity(TeacherDTO teacherDTO);

}
