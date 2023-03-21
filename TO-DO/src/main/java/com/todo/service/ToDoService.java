package com.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.controller.ToDoController;
import com.todo.model.Task;
import com.todo.repository.TaskRepository;

@Service
public class ToDoService implements ToDoServiceInterface{
	
	@Autowired
	private TaskRepository taskRepository;
	Logger logger = LoggerFactory.getLogger(ToDoController.class);
	
	public void addNewTask(Task task) {
		task.setId();
		taskRepository.save(task);
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task getTaskById(Long id) {
		return taskRepository.findById(id).get();
	}
	
	@Override
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}

	@Override
	public void saveUpdatedTask(Task existingTask, Task task) {
		existingTask.setName(task.getName());
		existingTask.setDescription(task.getDescription());
		existingTask.setDate(task.getDate());
		logger.trace("Updation: The existing element modified");
		taskRepository.save(existingTask);
	}

	@Override
	public List<Task> getSameDayTasks() {
        return taskRepository.findAll()
        		.stream()
        		.filter(n->n.getDate().equals(String.valueOf(java.time.LocalDate.now())))
        		.collect(Collectors.toList());
	}
	
}
