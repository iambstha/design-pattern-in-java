package com.bishal.factorydesign.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bishal.factorydesign.dto.StudentDTO;
import com.bishal.factorydesign.entity.Student;
import com.bishal.factorydesign.exception.RecordNotFoundException;
import com.bishal.factorydesign.mapper.StudentMapper;
import com.bishal.factorydesign.repository.StudentJpaRepository;
import com.bishal.factorydesign.service.template.ServiceTemplate;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class StudentService implements ServiceTemplate<StudentDTO> {
	@Autowired
	private final StudentJpaRepository studentJpaRepository;

	@Override
	public StudentDTO createEntity(StudentDTO studentDTO) {
		Student student = StudentMapper.INSTANCE.toEntity(studentDTO);
		student.setCreatedTs(new Timestamp(System.currentTimeMillis()));
		student.setCreatedBy(null);
		Student savedStudent = studentJpaRepository.save(student);
		return StudentMapper.INSTANCE.toDTO(savedStudent);
	}

	@Override
	public StudentDTO readEntity(Integer id) {
		Optional<Student> optionalStudent = studentJpaRepository.findById(id);
		if (!optionalStudent.isPresent()) {
			throw new RecordNotFoundException("Student with id " + id + " not found.");
		}
		return StudentMapper.INSTANCE.toDTO(optionalStudent.get());
	}

	@Override
	public StudentDTO updateEntity(Integer id, StudentDTO updatedEntity) {
		Optional<Student> optionalStudent = studentJpaRepository.findById(id);
		if (!optionalStudent.isPresent()) {
			throw new RecordNotFoundException("Student with id " + id + " not found.");
		}
		Student student = optionalStudent.get();
		student.setModifiedTs(new Timestamp(System.currentTimeMillis()));
		student.setModifiedBy(null);
		updatedEntity.setId(student.getId());
		StudentMapper.INSTANCE.updateStudentFromDto(updatedEntity, student);
		studentJpaRepository.save(student);
		return StudentMapper.INSTANCE.toDTO(student);
	}

	@Override
	public boolean deleteEntity(Integer id) {
		if (studentJpaRepository.existsById(id)) {
			studentJpaRepository.deleteById(id);
			return true;
		}
		throw new RecordNotFoundException("Student with id " + id + " not found.");
	}
}
