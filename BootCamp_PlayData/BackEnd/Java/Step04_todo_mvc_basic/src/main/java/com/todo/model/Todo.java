package com.todo.model;

import java.time.LocalDate;

public class Todo {
	public Long number; // 할 일 번호
	private String title; // 할 일 제목
	private String description; // 할 일 내용
	private LocalDate todoDueDate; // 할 일 날짜
	private Boolean isCompleted; // 진행 여부
	

	
	public Todo(String title, String description, LocalDate todoDueDate) {
		this.title = title;
		this.description = description;
		this.todoDueDate = todoDueDate;
	}

	public Todo(Long number, String title, String description, LocalDate todoDueDate) {
		this.number = number;
		this.title = title;
		this.description = description;
		this.todoDueDate = todoDueDate;
		
		isCompleted = false;
	}


	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTodoDueDate() {
		return todoDueDate;
	}

	public void setTodoDueDate(LocalDate todoDueDate) {
		this.todoDueDate = todoDueDate;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public void printTodo() {
		System.out.println(number + "번째 할 일은 ! " + title + " !");
		System.out.println("상세 내용은 '"+ description + "'(으)로 기한은 " + todoDueDate + "입니다.");
		System.out.println();
	}
	
	// 오버라이딩을 사용해서 위와 같은 방식 사용하기
	@Override
	public String toString() {

		return (number + "번째 할 일은 ! " + title + " !\n"
				+ "상세 내용은 '"+ description + "'(으)로 기한은 " 
				+ todoDueDate + "입니다.\n\n");
	}
	
}
