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
import com.bishal.factorydesign.dto.StudentDTO;
import com.bishal.factorydesign.service.factory.StudentServiceFactory;
import com.bishal.factorydesign.service.template.ServiceTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/students")
@RequiredArgsConstructor
public class StudentResource {

	@Autowired
	@Qualifier("studentServiceFactory")
	private final StudentServiceFactory abstractServiceFactory;

	@PostMapping
	public ResponseEntity<ErrorResponse> createStudent(@RequestBody StudentDTO studentDTO) {
		ServiceTemplate<StudentDTO> studentService = abstractServiceFactory.createStudentService();
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse(HttpStatus.CREATED, "CREATED", details);
		error.setData(studentService.createEntity(studentDTO));
		error.setStatusCode(201);
		return new ResponseEntity<>(error, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<ErrorResponse> readStudent(@PathVariable Integer id) {
		ServiceTemplate<StudentDTO> studentService = abstractServiceFactory.createStudentService();
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse(HttpStatus.OK, "SUCCESS", details);
		error.setData(studentService.readEntity(id));
		error.setStatusCode(200);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@PutExchange("{id}")
	public ResponseEntity<ErrorResponse> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
		ServiceTemplate<StudentDTO> studentService = abstractServiceFactory.createStudentService();
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse(HttpStatus.OK, "UPDATED", details);
		error.setData(studentService.updateEntity(id, studentDTO));
		error.setStatusCode(201);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ErrorResponse> deleteStudent(@PathVariable Integer id) {
		ServiceTemplate<StudentDTO> studentService = abstractServiceFactory.createStudentService();
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse(HttpStatus.OK, "DELETED", details);
		studentService.deleteEntity(id);
		error.setStatusCode(200);
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

}
