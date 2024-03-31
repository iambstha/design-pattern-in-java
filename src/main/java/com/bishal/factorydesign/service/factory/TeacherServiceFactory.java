package com.bishal.factorydesign.service.factory;

import org.springframework.stereotype.Component;

import com.bishal.factorydesign.dto.StudentDTO;
import com.bishal.factorydesign.dto.TeacherDTO;
import com.bishal.factorydesign.repository.TeacherJpaRepository;
import com.bishal.factorydesign.service.TeacherService;
import com.bishal.factorydesign.service.template.ServiceTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TeacherServiceFactory implements AbstractServiceFactory {

	private final TeacherJpaRepository teacherJpaRepository;

	@Override
	public ServiceTemplate<StudentDTO> createStudentService() {
		throw new UnsupportedOperationException("Student service creation not supported by TeacherServiceFactory");
	}

	@Override
	public ServiceTemplate<TeacherDTO> createTeacherService() {
		return new TeacherService(teacherJpaRepository);
	}
}