package com.bishal.factorydesign.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bishal.factorydesign.dto.StudentDTO;
import com.bishal.factorydesign.dto.TeacherDTO;
import com.bishal.factorydesign.mapper.StudentMapper;
import com.bishal.factorydesign.mapper.TeacherMapper;
import com.bishal.factorydesign.repository.StudentJpaRepository;
import com.bishal.factorydesign.repository.TeacherJpaRepository;
import com.bishal.factorydesign.service.EntityService;
import com.bishal.factorydesign.service.StudentService;
import com.bishal.factorydesign.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EntityServiceFactory {
	
	@Autowired
	private final StudentJpaRepository studentJpaRepository;
	@Autowired
	private final TeacherJpaRepository teacherJpaRepository;
	@Autowired
	private final StudentMapper studentMapper;
	@Autowired
	private final TeacherMapper teacherMapper;


	public EntityService<?> createService(String entityType) {
		if (entityType.equals("student")) {
			return new StudentService(studentMapper, studentJpaRepository);
		} else if (entityType.equals("teacher")) {
			return new TeacherService(teacherMapper, teacherJpaRepository);
		} else {
			throw new IllegalArgumentException("Unsupported entity type: " + entityType);
		}
	}

	public EntityService<TeacherDTO> createTeacherService() {
			return new TeacherService(teacherMapper, teacherJpaRepository);
	}

	public EntityService<StudentDTO> createStudentService() {
			return new StudentService(studentMapper, studentJpaRepository);
	}

}
