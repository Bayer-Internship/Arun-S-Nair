package com.todo.controller;

import java.util.InputMismatchException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo.model.Task;
import com.todo.service.ToDoService;

@Controller
public class ToDoController {
	
	@Autowired
	private ToDoService toDoService;
	Logger logger = LoggerFactory.getLogger(ToDoController.class);
	
	@RequestMapping("/index")
	public String getHome(Model model) {
		List<Task> t=toDoService.getAllTasks();	
		logger.trace("Fetched all tasks");
		model.addAttribute("taskList",t);
		model.addAttribute("today",toDoService.getSameDayTasks());
		logger.trace("Fetched all tasks for the current day");
		logger.info("Redirecting to home");
		return "home";
	}
	
	@GetMapping("/addNew")
	public String getAddForm(Model model) {
		model.addAttribute("task", new Task());
		logger.trace("Task object added to model");
		logger.info("Redirecting to Input form");
		return "NewToDo";
	}
	
	@PostMapping("/addTask")
	public String addTask(@ModelAttribute Task task, Model model){
		try {
			if(task.getName().equals("")) {
				model.addAttribute("msg1","Field cannot be empty");
				throw new InputMismatchException("Exception thrown: Input contains null");
			}
			else {
				toDoService.addNewTask(task);
				logger.debug("Task inserted");
			}
		}catch (InputMismatchException e) {
			logger.debug(e.getMessage());
			return "NewToDo";
		}
		logger.info("Redirecting to home");
		return "redirect:/index";
		
	}
	
	@GetMapping("/update/{id}")
	public String fetchTaskForUpdate(@PathVariable Long id, Model model) {
		model.addAttribute("updateTask", toDoService.getTaskById(id));
		logger.debug("Task with the given id fetched");
		logger.info("Redirecting to updation form");
		return "UpdateForm";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable Long id) {
		toDoService.deleteTask(id);
		logger.debug("Task deleted");
		logger.info("Redirecting to home");
		return "redirect:/index";
	}
	
	@PostMapping("/change/{id}")
	public String updateTask(@PathVariable Long id, @ModelAttribute Task task, Model model) {
		Task existingTask=toDoService.getTaskById(id);
		toDoService.saveUpdatedTask(existingTask,task);
		logger.info("Redirecting to home");
		return "redirect:/index";
	}
	
	
}
