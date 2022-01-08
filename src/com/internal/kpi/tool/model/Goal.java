package com.internal.kpi.tool.model;

public class Goal {

	private int id;
	private String name;
	private String description;
	private int assignToUserId;
	private int createdByUserId;
	private String date;
	private int completed;
	private String assignToUserEmail;
	private String createdByUserEmail;

	public Goal(int id, String name, String description, int assignToId, int createdByUserId, String date, int completed) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.assignToUserId = assignToId;
		this.createdByUserId = createdByUserId;
		this.date=date;
		this.completed = completed;
	}
	
	public Goal(String name, String description, int assignToId, int createdByUserId, String date, int completed) {
		this.name = name;
		this.description = description;
		this.assignToUserId = assignToId;
		this.createdByUserId = createdByUserId;
		this.date = date;
		this.completed = completed;
	}

	public int getAssignToUserId() {
		return assignToUserId;
	}

	public void setAssignToUserId(int assignToUserId) {
		this.assignToUserId = assignToUserId;
	}

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(int createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public String getAssignToUserEmail() {
		return assignToUserEmail;
	}

	public void setAssignToUserEmail(String assignToUserEmail) {
		this.assignToUserEmail = assignToUserEmail;
	}

	public String getCreatedByUserEmail() {
		return createdByUserEmail;
	}

	public void setCreatedByUserEmail(String createdByUserEmail) {
		this.createdByUserEmail = createdByUserEmail;
	}
	
}
