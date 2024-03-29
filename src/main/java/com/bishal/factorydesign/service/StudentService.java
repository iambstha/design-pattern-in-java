package com.bishal.factorydesign.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bishal.factorydesign.dto.StudentDTO;
import com.bishal.factorydesign.entity.Student;
import com.bishal.factorydesign.exception.RecordNotFoundException;
import com.bishal.factorydesign.mapper.StudentMapper;
import com.bishal.factorydesign.repository.StudentJpaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class StudentService implements EntityService<StudentDTO> {
	@Autowired
	private final StudentMapper studentMapper;
	@Autowired
	private final StudentJpaRepository studentJpaRepository;

	@Override
	public StudentDTO createEntity(StudentDTO studentDTO) {
		Student student = studentMapper.toEntity(studentDTO);
		Student savedStudent = studentJpaRepository.save(student);
		return studentMapper.toDTO(savedStudent);
	}

	@Override
	public StudentDTO readEntity(Integer id) {
		Optional<Student> optionalStudent = studentJpaRepository.findById(id);
		if (!optionalStudent.isPresent()) {
			throw new RecordNotFoundException("Student with id " + id + " not found.");
		}
		return studentMapper.toDTO(optionalStudent.get());
	}

	@Override
	public StudentDTO updateEntity(Integer id, StudentDTO updatedEntity) {
		Optional<Student> optionalStudent = studentJpaRepository.findById(id);
		if (!optionalStudent.isPresent()) {
			throw new RecordNotFoundException("Student with id " + id + " not found.");
		}
		Student student = optionalStudent.get();
		student.setFirstName(updatedEntity.getFirstName());
		student.setLastName(updatedEntity.getLastName());
		student.setGrade(updatedEntity.getGrade());
		return studentMapper.toDTO(student);
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
