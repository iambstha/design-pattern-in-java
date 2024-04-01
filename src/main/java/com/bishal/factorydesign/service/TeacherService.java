package com.bishal.factorydesign.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bishal.factorydesign.dto.TeacherDTO;
import com.bishal.factorydesign.entity.Teacher;
import com.bishal.factorydesign.exception.RecordNotFoundException;
import com.bishal.factorydesign.mapper.TeacherMapper;
import com.bishal.factorydesign.repository.TeacherJpaRepository;
import com.bishal.factorydesign.service.template.ServiceTemplate;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class TeacherService implements ServiceTemplate<TeacherDTO> {
	@Autowired
	private final TeacherJpaRepository teacherJpaRepository;

	@Override
	public TeacherDTO createEntity(TeacherDTO teacherDTO) {
		Teacher teacher = TeacherMapper.INSTANCE.toEntity(teacherDTO);
		teacher.setCreatedTs(new Timestamp(System.currentTimeMillis()));
		teacher.setCreatedBy(null);
		Teacher savedTeacher = teacherJpaRepository.save(teacher);
		return TeacherMapper.INSTANCE.toDTO(savedTeacher);
	}

	@Override
	public TeacherDTO readEntity(Integer id) {
		Optional<Teacher> optionalTeacher = teacherJpaRepository.findById(id);
		if (!optionalTeacher.isPresent()) {
			throw new RecordNotFoundException("Teacher with id " + id + " not found.");
		}
		return TeacherMapper.INSTANCE.toDTO(optionalTeacher.get());
	}

	@Override
	public TeacherDTO updateEntity(Integer id, TeacherDTO updatedEntity) {
		Optional<Teacher> optionalTeacher = teacherJpaRepository.findById(id);
		if (!optionalTeacher.isPresent()) {
			throw new RecordNotFoundException("Teacher with id " + id + " not found.");
		}
		Teacher teacher = optionalTeacher.get();
		teacher.setModifiedTs(new Timestamp(System.currentTimeMillis()));
		teacher.setModifiedBy(null);
		updatedEntity.setId(teacher.getId());
		TeacherMapper.INSTANCE.updateTeacherFromDto(updatedEntity, teacher);
		teacherJpaRepository.save(teacher);
		return TeacherMapper.INSTANCE.toDTO(teacher);
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