package com.internal.kpi.tool.model;

public class Team {
	private int id;
	private String name;
	private int manager_id;
	private String manager_email;

	public Team(int id, String name, int manager_id) {
		super();
		this.id = id;
		this.name = name;
		this.manager_id = manager_id;
	}

	public Team(String name, int manager_id) {
		super();
		this.name = name;
		this.manager_id = manager_id;
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

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public String getManager_email() {
		return manager_email;
	}

	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}

}
