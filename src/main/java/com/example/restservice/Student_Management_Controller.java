package com.example.restservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Student_Management_Controller {

	private static Map<String, Student_Management> studentRepo = new HashMap<>();

	static {
		Student_Management student1 = new Student_Management();
		student1.setStudNumber("C21101131");
		student1.setStudName("Mark Angelo S. Fulledo");
		student1.setStudCourse("Bachelor of Science in Information Technology");
		student1.setStudYear(3);
		student1.setStudSection('A');
		student1.setStudAge(21);
		student1.setStudAddress("Zone 1 Jornala Street, Balaogan Bula Camarines Sur");

		studentRepo.put(student1.getStudNumber(), student1);
	}

	// GET Request
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ResponseEntity<Object> getStudent() {
		return new ResponseEntity<>(studentRepo.values(), HttpStatus.OK);
	}

	// POST Request
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public ResponseEntity<Object> createStudent(@RequestBody Student_Management student) {
		studentRepo.put(student.getStudNumber(), student);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}

	// UPDATE Request
	@RequestMapping(value = "/student/{studNumber}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateStudent(@PathVariable("studNumber") String studNumber,
			@RequestBody Student_Management student) {
		studentRepo.remove(studNumber);
		student.setStudNumber(studNumber);
		studentRepo.put(studNumber, student);
		return new ResponseEntity<>("Student successfully updated in the list.", HttpStatus.OK);
	}

	// DELETE Request
	@RequestMapping(value = "/student/{studNumber}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteStudent(@PathVariable("studNumber") String studNumber) {
		studentRepo.remove(studNumber);
		return new ResponseEntity<>("Student removed from the list.", HttpStatus.OK);
	}
}
