package com.bishal.factorydesign.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.bishal.factorydesign.domain.ErrorResponse;
import com.bishal.factorydesign.dto.TeacherDTO;
import com.bishal.factorydesign.service.factory.TeacherServiceFactory;
import com.bishal.factorydesign.service.template.ServiceTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/teachers")
@RequiredArgsConstructor
public class TeacherResource {

	@Autowired
	@Qualifier("teacherServiceFactory")
	private final TeacherServiceFactory abstractServiceFactory;

	@PostMapping
	public ResponseEntity<ErrorResponse> createTeacher(@RequestBody TeacherDTO teacherDTO) {
		ServiceTemplate<TeacherDTO> teacherService = abstractServiceFactory.createTeacherService();
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse(HttpStatus.CREATED, "CREATED", details);
		error.setData(teacherService.createEntity(teacherDTO));
		error.setStatusCode(201);
		return new ResponseEntity<>(error, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<ErrorResponse> readTeacher(@PathVariable Integer id) {
		ServiceTemplate<TeacherDTO> teacherService = abstractServiceFactory.createTeacherService();
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse(HttpStatus.OK, "SUCCESS", details);
		error.setData(teacherService.readEntity(id));
		error.setStatusCode(200);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@PutExchange("{id}")
	public ResponseEntity<ErrorResponse> updateTeacher(@PathVariable Integer id, @RequestBody TeacherDTO teacherDTO) {
		ServiceTemplate<TeacherDTO> teacherService = abstractServiceFactory.createTeacherService();
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse(HttpStatus.OK, "UPDATED", details);
		error.setData(teacherService.updateEntity(id, teacherDTO));
		error.setStatusCode(201);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ErrorResponse> deleteTeacher(@PathVariable Integer id) {
		ServiceTemplate<TeacherDTO> teacherService = abstractServiceFactory.createTeacherService();
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse(HttpStatus.OK, "DELETED", details);
		teacherService.deleteEntity(id);
		error.setStatusCode(200);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

}
