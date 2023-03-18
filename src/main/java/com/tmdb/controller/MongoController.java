package com.tmdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.tmdb.model.Student;
import com.tmdb.service.StudentService;

@Controller
public class MongoController {
	@Autowired
	StudentService studentservice;
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("stud", new Student());
		return "index";
	}
	@PostMapping("register")
	public String reg(@ModelAttribute Student student, Model model) {
		studentservice.saveStudent(student);
		return "postreg";
	}
	
	@GetMapping("/show")
	public String show(Model model ) {
		model.addAttribute("students",studentservice.getAllStudents());
		return "view";
	}
	
	@GetMapping("/edit/{id}")
	public String up(@PathVariable Integer id,Model model) {
		model.addAttribute("updateStud", studentservice.getStudentById(id));
		return "updata";
	}
	
	@PostMapping("/update/{id}")
	public String up(@PathVariable Integer id, @ModelAttribute Student student, Model model){
		System.out.println("up1");
		Student ex=studentservice.getStudentById(id);
		ex.setId(id);
		ex.setName(student.getName());
		studentservice.saveStudent(ex);
		model.addAttribute("stud", new Student());
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String del(@PathVariable Integer id){
		studentservice.deleteStudent(id);
		return "redirect:/";
	}
}
