package br.ce.wcaquino.taskbackend.model;

import java.time.LocalDate;

public class TaskBuilder {
	
	private LocalDate dueDate = null;
	private String task = null;
	
	private TaskBuilder() {}
	
	public static TaskBuilder aTask() {
		return new TaskBuilder();
	}
	
	public TaskBuilder withDueData(LocalDate date) {
		this.dueDate = date;
		return this;
	}
	
	public TaskBuilder withTask(String task) {
		this.task  = task;
		return this;
	}
	
	public Task build() {
		Task todo = new Task();
		todo.setTask(task);
		todo.setDueDate(this.dueDate);
		return todo;
	}
	

}
