package com.internal.kpi.tool.model;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String role;
	private String email;
	private String password;
	private String permissions;
	private String secret_key;
	private Team team;

	public User(int id, String firstName, String lastName, String role, String email, String password,
			String permissions, String secret_key) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.password = password;
		this.permissions = permissions;
		this.secret_key = secret_key;
	}
	
	public User(String firstName, String lastName, String role, String email, String password, String permissions) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.password = password;
		this.permissions = permissions;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getPermissions() {
		return permissions;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public String getTeamName() {
		String name = "No team";
		if (team != null) {
			name = team.getName();
		}
		return name;
	}

	public String getSecret_key() {
		return secret_key;
	}

	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}

	@Override
	public String toString() {
		return "UserDAO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role
				+ ", email=" + email + ", password=" + password + ", permissions=" + permissions + "]";
	}

}
