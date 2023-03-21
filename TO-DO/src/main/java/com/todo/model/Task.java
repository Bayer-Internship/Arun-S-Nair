package com.todo.model;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.todo.controller.ToDoController;

@Document(collection = "Tasks")
public class Task {
	@Transient
	Logger logger = LoggerFactory.getLogger(ToDoController.class);
	@Id
	private Long id;
	private String name;
	private String description;
	private String date;

	public Task() {
		super();
	}
	
	public Task(Long id, String name, String description, String date) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
	}
	
	public Long getId() {
		return id;
	}

	public void setId() {
		Date date = new Date();
	    id = (Long) date.getTime();
	    logger.trace("Task created with id:"+id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + "]";
	}

}
