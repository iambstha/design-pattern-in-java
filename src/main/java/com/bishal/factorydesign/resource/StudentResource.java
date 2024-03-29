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

import com.bishal.factorydesign.dto.StudentDTO;
import com.bishal.factorydesign.factory.EntityServiceFactory;
import com.bishal.factorydesign.service.EntityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/students")
@RequiredArgsConstructor
public class StudentResource {

	@Autowired
	private final EntityServiceFactory entityServiceFactory;

	@PostMapping
	public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
		EntityService<StudentDTO> studentService = entityServiceFactory.createStudentService();
		return studentService.createEntity(studentDTO);
	}

	@GetMapping("{id}")
	public StudentDTO readStudent(@PathVariable Integer id) {
		EntityService<StudentDTO> studentService = entityServiceFactory.createStudentService();
		return studentService.readEntity(id);
	}

	@PutExchange("{id}")
	public StudentDTO updateStudent(@PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
		EntityService<StudentDTO> studentService = entityServiceFactory.createStudentService();
		return studentService.updateEntity(id, studentDTO);
	}

	@DeleteMapping("{id}")
	public boolean deleteStudent(@PathVariable Integer id) {
		EntityService<StudentDTO> studentService = entityServiceFactory.createStudentService();
		return studentService.deleteEntity(id);
	}

}
