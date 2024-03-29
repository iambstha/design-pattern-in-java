package com.bishal.factorydesign.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bishal.factorydesign.dto.TeacherDTO;
import com.bishal.factorydesign.entity.Teacher;
import com.bishal.factorydesign.exception.RecordNotFoundException;
import com.bishal.factorydesign.mapper.TeacherMapper;
import com.bishal.factorydesign.repository.TeacherJpaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class TeacherService implements EntityService<TeacherDTO> {
	@Autowired
	private final TeacherMapper teacherMapper;
	@Autowired
	private final TeacherJpaRepository teacherJpaRepository;

	@Override
	public TeacherDTO createEntity(TeacherDTO teacherDTO) {
		Teacher savedTeacher = teacherJpaRepository.save(teacherMapper.toEntity(teacherDTO));
		return teacherMapper.toDTO(savedTeacher);
	}

	@Override
	public TeacherDTO readEntity(Integer id) {
		Optional<Teacher> optionalTeacher = teacherJpaRepository.findById(id);
		if (!optionalTeacher.isPresent()) {
			throw new RecordNotFoundException("Teacher with id " + id + " not found.");
		}
		return teacherMapper.toDTO(optionalTeacher.get());
	}

	@Override
	public TeacherDTO updateEntity(Integer id, TeacherDTO updatedEntity) {
		Optional<Teacher> optionalTeacher = teacherJpaRepository.findById(id);
		if (!optionalTeacher.isPresent()) {
			throw new RecordNotFoundException("Teacher with id " + id + " not found.");
		}
		Teacher teacher = optionalTeacher.get();
		teacher.setFirstName(updatedEntity.getFirstName());
		teacher.setLastName(updatedEntity.getLastName());
		teacher.setSubject(updatedEntity.getSubject());
		return teacherMapper.toDTO(teacher);
	}

	@Override
	public boolean deleteEntity(Integer id) {
		if (teacherJpaRepository.existsById(id)) {
			teacherJpaRepository.deleteById(id);
			return true;
		}
		throw new RecordNotFoundException("Teacher with id " + id + " not found.");
	}

}