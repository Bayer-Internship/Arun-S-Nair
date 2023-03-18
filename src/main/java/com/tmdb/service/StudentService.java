package com.tmdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdb.model.Student;
import com.tmdb.repository.StudentRepository;
@Service
public class StudentService {
	@Autowired
	private StudentRepository studentrepo;
	
	public void saveStudent(Student student) {
		studentrepo.save(student);
	}
	
	public List<Student> getAllStudents(){
		return studentrepo.findAll();
	}

	public Student getStudentById(Integer id) {
		return studentrepo.findById(id).get();
	}
	public void updateStudent(Student student) {
		studentrepo.save(student);
	}
	public void deleteStudent(Integer id) {
		studentrepo.deleteById(id);
	}
}
