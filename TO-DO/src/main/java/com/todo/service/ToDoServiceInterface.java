package com.todo.service;

import java.util.List;
import com.todo.model.Task;

public interface ToDoServiceInterface {
	public void addNewTask(Task task);
	public List<Task> getAllTasks();
	public Task getTaskById(Long id);
	public void deleteTask(Long id);
	public void saveUpdatedTask(Task existingTask,Task task);
	public List<Task> getSameDayTasks();
}
