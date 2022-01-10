package com.internal.kpi.tool.model;

public class Task {

	private int id;
	private String name;
	private String description;
	private int ownerId;
	private Goal goal;
	private Team team;
	private String queueNumber;
	private String ownerEmail;

	public Task(int id, String name, String description, int ownerId, Goal goal, String queueNumber, Team team) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.ownerId = ownerId;
		this.goal = goal;
		this.queueNumber = queueNumber;
		this.team = team;
	}
	
	public Task(String name, String description, int ownerId, Goal goal, String queueNumber) {
		this.name = name;
		this.description = description;
		this.ownerId = ownerId;
		this.goal = goal;
		this.queueNumber = queueNumber;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
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

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public String getQueueNumber() {
		return queueNumber;
	}

	public void setQueueNumber(String queueNumber) {
		this.queueNumber = queueNumber;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", ownerId=" + ownerId + ", goal="
				+ goal + ", team=" + team + ", queueNumber=" + queueNumber + ", ownerEmail=" + ownerEmail + "]";
	}

	

}
