package com.bishal.factorydesign.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.bishal.factorydesign.dto.TeacherDTO;
import com.bishal.factorydesign.factory.EntityServiceFactory;
import com.bishal.factorydesign.service.EntityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/teachers")
@RequiredArgsConstructor
public class TeacherResource {

	@Autowired
	private final EntityServiceFactory entityServiceFactory;

	@PostMapping
	public TeacherDTO createTeacher(@RequestBody TeacherDTO teacherDTO) {
		EntityService<TeacherDTO> teacherService = entityServiceFactory.createTeacherService();
		return teacherService.createEntity(teacherDTO);
	}

	@GetMapping("{id}")
	public TeacherDTO readTeacher(@PathVariable Integer id) {
		EntityService<TeacherDTO> teacherService = entityServiceFactory.createTeacherService();
		return teacherService.readEntity(id);
	}

	@PutExchange("{id}")
	public TeacherDTO updateTeacher(@PathVariable Integer id, @RequestBody TeacherDTO teacherDTO) {
		EntityService<TeacherDTO> teacherService = entityServiceFactory.createTeacherService();
		return teacherService.updateEntity(id, teacherDTO);
	}

	@DeleteMapping("{id}")
	public boolean deleteTeacher(@PathVariable Integer id) {
		EntityService<TeacherDTO> teacherService = entityServiceFactory.createTeacherService();
		return teacherService.deleteEntity(id);
	}

}
