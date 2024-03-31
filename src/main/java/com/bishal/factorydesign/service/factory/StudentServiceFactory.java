package com.bishal.factorydesign.service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bishal.factorydesign.dto.StudentDTO;
import com.bishal.factorydesign.dto.TeacherDTO;
import com.bishal.factorydesign.repository.StudentJpaRepository;
import com.bishal.factorydesign.service.StudentService;
import com.bishal.factorydesign.service.template.ServiceTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudentServiceFactory implements AbstractServiceFactory {

	@Autowired
	private final StudentJpaRepository studentJpaRepository;

	@Override
	public ServiceTemplate<StudentDTO> createStudentService() {
		return new StudentService(studentJpaRepository);
	}

	@Override
	public ServiceTemplate<TeacherDTO> createTeacherService() {
		throw new UnsupportedOperationException("Teacher service creation not supported by StudentServiceFactory");
	}

}
