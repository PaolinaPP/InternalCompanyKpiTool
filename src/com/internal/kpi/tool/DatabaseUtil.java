package com.internal.kpi.tool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internal.kpi.tool.model.Goal;
import com.internal.kpi.tool.model.Task;
import com.internal.kpi.tool.model.Team;
import com.internal.kpi.tool.model.User;

public class DatabaseUtil {

	public static int getUserId(DBConnection conn, String email) {
		String selectQuery = "select id from users where email=?";
		List<String> queryParams = new ArrayList<String>();
		int id = 0;
		ResultSet rs = null;
		queryParams.add(email);
		try {
			rs = conn.query(selectQuery, queryParams);
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static List<String> getAllTeamMembers(DBConnection conn, int id) {
		List<String> teamMembersEmails = new ArrayList<String>();
		List<String> queryParams = new ArrayList<String>();
		String selectSQL = "SELECT users.email FROM user_teams JOIN users ON users.id= user_teams.user_id JOIN teams ON teams.id= user_teams.team_id WHERE teams.manager_id= ?";
		queryParams.add(id + "");
		ResultSet rs = null;
		try {
			rs = conn.query(selectSQL, queryParams);
			while (rs.next()) {
				teamMembersEmails.add(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teamMembersEmails;
	}

	public static List<String> getAllManagers(DBConnection conn) {
		List<String> managers = new ArrayList<String>();
		List<String> queryParams = new ArrayList<String>();
		String selectSQL = "select email from users where permissions=?";
		queryParams.add("manager");
		ResultSet rs = null;
		try {
			rs = conn.query(selectSQL, queryParams);
			while (rs.next()) {
				managers.add(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return managers;
	}

	public static void addNewGoal(DBConnection conn, Goal goal) {
		String insertGoal = "INSERT INTO goals(name, description, assigned_to_user_id, created_by_user_id) values(?,?,?,?)";
		List<String> queryParams = new ArrayList<String>();
		queryParams.add(goal.getName());
		queryParams.add(goal.getDescription());
		queryParams.add(Integer.toString(goal.getAssignToUserId()));
		queryParams.add(Integer.toString(goal.getCreatedByUserId()));
		try {
			conn.insert(insertGoal, queryParams);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<String> getAllGoals(DBConnection conn, int assignedToId) {
		List<String> goals = new ArrayList<String>();
		List<String> queryParams = new ArrayList<String>();
		String selectSQL = "SELECT name FROM goals WHERE assigned_to_user_id=?";
		queryParams.add(Integer.toString(assignedToId));
		ResultSet rs = null;
		try {
			rs = conn.query(selectSQL, queryParams);
			while (rs.next()) {
				goals.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goals;
	}

	public static void addNewTask(DBConnection conn, Task task) {
		String insertGoal = "INSERT INTO tasks(name, description, owner_id, goal_id, queue_number, team_id) values(?,?,?,?,?,?)";
		List<String> queryParams = new ArrayList<String>();
		queryParams.add(task.getName());
		queryParams.add(task.getDescription());
		queryParams.add(Integer.toString(task.getOwnerId()));
		queryParams.add(Integer.toString(task.getGoal().getId()));
		queryParams.add(task.getQueueNumber());
		queryParams.add(Integer.toString(task.getTeam().getId()));
		try {
			conn.insert(insertGoal, queryParams);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addNewTeam(DBConnection conn, Team newTeam) {
		List<String> queryParams = new ArrayList<String>();
		String insertTeam = "INSERT INTO teams(name, manager_id) values(?,?)";
		queryParams.add(newTeam.getName());
		queryParams.add(newTeam.getManager_id() + "");
		try {
			conn.insert(insertTeam, queryParams);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Team getTeam(DBConnection conn, int userId) {
		List<String> queryParams = new ArrayList<String>();
		Team team = null;
		String selectTeamSQL = "SELECT teams.* FROM user_teams JOIN teams ON teams.id= user_teams.team_id JOIN users ON users.id= user_teams.user_id WHERE users.id=?";
		queryParams.add(userId + "");
		ResultSet rs = null;
		try {
			rs = conn.query(selectTeamSQL, queryParams);
			while (rs.next()) {
				team = new Team(rs.getInt("id"), rs.getString("name"), rs.getInt("manager_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return team;
	}

	public static Goal getGoalAssignedToSpecificUser(DBConnection conn, int userId, String name) {
		Goal goal = null;
		List<String> queryParams = new ArrayList<String>();
		String sql = "SELECT * FROM goals WHERE assigned_to_user_id = ? and name=?";
		queryParams.add(userId + "");
		queryParams.add(name);
		ResultSet rs = null;
		try {
			rs = conn.query(sql, queryParams);
			while (rs.next()) {
				goal = new Goal(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("assigned_to_user_id"), rs.getInt("created_by_user_id"),
						rs.getString("created_on_date"), rs.getInt("completed"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goal;
	}

	public static Team getTeam(DBConnection conn, String teamName) {
		List<String> queryParams = new ArrayList<String>();
		List<String> queryParams2 = new ArrayList<String>();
		Team team = null;
		String selectTeamSQL = "select * from teams where name=?";
		queryParams.add(teamName);
		String selectManagerEmail = "select email from users where id=?";
		ResultSet rs = null;
		try {
			rs = conn.query(selectTeamSQL, queryParams);
			while (rs.next()) {
				team = new Team(rs.getInt("id"), rs.getString("name"), rs.getInt("manager_id"));
			}
			queryParams2.add(team.getManager_id() + "");
			rs = conn.query(selectManagerEmail, queryParams2);
			while (rs.next()) {
				team.setManager_email(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return team;
	}

	public static void addNewUser(DBConnection conn, User userToAdd, String secretKey) throws Exception {
		SecureData secureSecretKey = new SecureData(secretKey);
		List<String> queryParams = new ArrayList<String>();
		String insertUser = "INSERT INTO users(first_name, last_name, role, email, pass, permissions, secret_key) values(?,?,?,?,?,?,?)";
		queryParams.add(userToAdd.getFirstName());
		queryParams.add(userToAdd.getLastName());
		queryParams.add(userToAdd.getRole());
		queryParams.add(userToAdd.getEmail());
		queryParams.add(userToAdd.getPassword());
		queryParams.add(userToAdd.getPermissions());
		queryParams.add(secureSecretKey.secureAndVerifyString());
		try {
			conn.insert(insertUser, queryParams);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addRelationUserTeam(DBConnection conn, User userToAdd) {
		List<String> queryParams = new ArrayList<String>();
		String insertUserTeams = "INSERT INTO user_teams(user_id, team_id) values(?,?)";
		queryParams.add(getUserId(conn, userToAdd.getEmail()) + "");
		queryParams.add(userToAdd.getTeam().getId() + "");
		try {
			conn.insert(insertUserTeams, queryParams);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getAllTeams(DBConnection conn) {
		List<String> teamNames = new ArrayList<String>();
		List<String> queryParams = new ArrayList<String>();
		String selectSQL = "SELECT name FROM teams";
		ResultSet rs = null;
		try {
			rs = conn.query(selectSQL, queryParams);
			while (rs.next()) {
				teamNames.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teamNames;
	}

	public static User getUser(DBConnection conn, int id) throws SQLException {
		User existingUser = null;
		List<String> queryParams = new ArrayList<String>();
		String sql = "SELECT * FROM users WHERE id = ?";
		queryParams.add(id + "");
		ResultSet rs = null;
		try {
			rs = conn.query(sql, queryParams);
			while (rs.next()) {
				existingUser = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("role"), rs.getString("email"), rs.getString("pass"), rs.getString("permissions"),
						rs.getString("secret_key"));
				existingUser.setTeam(getTeam(conn, existingUser.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existingUser;
	}
	
	public static Goal getGoal(DBConnection conn, int id) throws SQLException {
		Goal goal = null;
		List<String> queryParams = new ArrayList<String>();
		String sql = "SELECT * FROM goals WHERE id = ?";
		queryParams.add(id + "");
		ResultSet rs = null;
		try {
			rs = conn.query(sql, queryParams);
			while (rs.next()) {
				goal = new Goal(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("assigned_to_user_id"), rs.getInt("created_by_user_id"),
						rs.getString("created_on_date"), rs.getInt("completed"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goal;
	}
	
	public static void deleteGoal(DBConnection conn, int id) {
		List<String> queryParams = new ArrayList<String>();
		String sql = "delete from goals WHERE id = ?";
		queryParams.add(id + "");
		delete(conn, sql, queryParams);
	}
	
	public static void deleteTask(DBConnection conn, int id) {
		List<String> queryParams = new ArrayList<String>();
		String sql = "delete from tasks WHERE id = ?";
		queryParams.add(id + "");
		delete(conn, sql, queryParams);
	}
	
	public static void deleteUser(DBConnection conn, int id) {
		List<String> queryParams = new ArrayList<String>();
		String sql = "delete from users WHERE id = ?";
		queryParams.add(id + "");
		delete(conn, sql, queryParams);
	}

	public static void deleteUserTasks(DBConnection conn, int id) {
		List<String> queryParams = new ArrayList<String>();
		String sql = "delete from tasks WHERE owner_id = ?";
		queryParams.add(id + "");
		delete(conn, sql, queryParams);
	}

	public static void deleteUserGoals(DBConnection conn, int id) {
		List<String> queryParams = new ArrayList<String>();
		String sql = "delete from goals WHERE assigned_to_user_id = ?";
		queryParams.add(id + "");
		delete(conn, sql, queryParams);
	}

	public static void deleteUserTeams(DBConnection conn, int id) {
		List<String> queryParams = new ArrayList<String>();
		String sql = "delete from user_teams WHERE user_id = ?";
		queryParams.add(id + "");
		delete(conn, sql, queryParams);
	}

	public static void delete(DBConnection conn, String sql, List<String> queryParams) {
		try {
			conn.insert(sql, queryParams);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Task getTask(DBConnection conn, int id) throws SQLException {
		Task task = null;
		List<String> queryParams = new ArrayList<String>();
		String sql = "SELECT * FROM tasks WHERE id = ?";
		queryParams.add(id + "");
		ResultSet rs = null;
		try {
			rs = conn.query(sql, queryParams);
			while (rs.next()) {
				task = new Task(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("owner_id"), getGoal(conn, rs.getInt("goal_id")), rs.getString("queue_number"),
						getTeam(conn, rs.getInt("owner_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}
	
	public static User getUserByEmail(DBConnection conn, String email) {
		User user = null;
		String selectQuery = "select * from users where email=?";
		List<String> queryParams = new ArrayList<String>();
		ResultSet rs = null;
		queryParams.add(email);
		try {
			rs = conn.query(selectQuery, queryParams);
			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("role"), rs.getString("email"), rs.getString("pass"), rs.getString("permissions"),
						rs.getString("secret_key"));
				user.setTeam(getTeam(conn, user.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static void updateGoal(DBConnection conn, Goal goalToEdit, Goal existingGoal) {
		List<String> queryParams = new ArrayList<String>();
		String updateGoal = "update goals set name=?, description=?, assigned_to_user_id=?, created_by_user_id=?, created_on_date=?, completed=? where id='"
				+ existingGoal.getId() + "'";
		queryParams.add(goalToEdit.getName());
		queryParams.add(goalToEdit.getDescription());
		queryParams.add(goalToEdit.getAssignToUserId()+"");
		queryParams.add(goalToEdit.getCreatedByUserId()+"");
		queryParams.add(goalToEdit.getDate());
		queryParams.add(goalToEdit.getCompleted()+"");
		try {
			conn.insert(updateGoal, queryParams);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getUserEmail(DBConnection conn, int id) {
		String email = null;
		List<String> queryParams = new ArrayList<String>();
		String sql = "SELECT * FROM users WHERE id = ?";
		queryParams.add(id + "");
		ResultSet rs = null;
		try {
			rs = conn.query(sql, queryParams);
			while (rs.next()) {
				email = rs.getString("email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return email;
	}
	
	public static String getTeamName(DBConnection conn, int id) {
		String name = null;
		List<String> queryParams = new ArrayList<String>();
		String sql = "SELECT teams.name FROM user_teams JOIN teams ON teams.id= user_teams.team_id WHERE user_teams.user_id= ?";
		queryParams.add(id + "");
		ResultSet rs = null;
		try {
			rs = conn.query(sql, queryParams);
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public static void editUser(DBConnection conn, User userToEdit, User existingUser) {
		List<String> queryParams = new ArrayList<String>();
		if (VerifyData.isValidEmail(userToEdit.getEmail())) {
			String insertUser = "update users set first_name=?, last_name=?, role=?, email=?, pass=?, permissions=?"
					+ " where id='" + existingUser.getId() + "'";
			queryParams.add(userToEdit.getFirstName());
			queryParams.add(userToEdit.getLastName());
			queryParams.add(userToEdit.getRole());
			queryParams.add(userToEdit.getEmail());
			queryParams.add(userToEdit.getPassword());
			queryParams.add(userToEdit.getPermissions());
			try {
				conn.insert(insertUser, queryParams);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void editRelationUserTeam(DBConnection conn, User userToEdit, User existingUser) {
		List<String> queryParams = new ArrayList<String>();
		String update = "update user_teams set team_id=? where user_id=?";
		queryParams.add(userToEdit.getTeam().getId() + "");
		queryParams.add(existingUser.getId() + "");
		try {
			conn.insert(update, queryParams);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Team getTeamById(DBConnection conn, int id) {
		Team team = null;
		List<String> queryParams = new ArrayList<String>();
		String sql = "SELECT teams.* FROM user_teams JOIN teams ON teams.id= user_teams.team_id WHERE user_teams.user_id= ?";
		queryParams.add(id + "");
		ResultSet rs = null;
		try {
			rs = conn.query(sql, queryParams);
			while (rs.next()) {
				team = new Team(rs.getInt("id"), rs.getString("name"), rs.getInt("manager_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(team.getName());
		return team;
	}
	
	public static String hashPassword(String defaultPass) {
		String hashedPassword = null;
		try {
			hashedPassword = SecureData.hashPassword(defaultPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashedPassword;
	}
	
	public static User configUser(DBConnection conn, String username) {
		User configuredUser = null;
		List<String> queryParams = new ArrayList<String>();
		String selectSQL = "SELECT * FROM users WHERE email = ?";
		queryParams.add(username);
		ResultSet rs = null;
		try {
			rs = conn.query(selectSQL, queryParams);
			while (rs.next()) {
				configuredUser = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("role"), rs.getString("email"), rs.getString("pass"), rs.getString("permissions"),
						rs.getString("secret_key"));
				configuredUser.setTeam(getTeamById(conn, configuredUser.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return configuredUser;
	}

	public static String getUserPermissions(DBConnection conn, String email) {
		String selectQuery = "select permissions from users where email=?";
		List<String> queryParams = new ArrayList<String>();
		String permissions = null;
		ResultSet rs = null;
		queryParams.add(email);
		try {
			rs = conn.query(selectQuery, queryParams);
			while (rs.next()) {
				permissions = rs.getString("permissions");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return permissions;
	}
	
	public static String getUserPassword(DBConnection conn, String user) {
		List<String> queryParams = new ArrayList<String>();
		String password = null;
		String selectSQL = "SELECT pass FROM users WHERE email = ?";
		queryParams.add(user);
		ResultSet rs = null;
		try {
			rs = conn.query(selectSQL, queryParams);
			while (rs.next()) {
				password = rs.getString("pass");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}

	public static void updateUserPassword(DBConnection conn, String user, String newHashedPassword,
			String oldHashedPassword) {
		List<String> queryParams = new ArrayList<String>();
		String updateSQL = "UPDATE users SET pass = ? WHERE email = ? AND pass = ?";
		queryParams.add(newHashedPassword);
		queryParams.add(user);
		queryParams.add(oldHashedPassword);
		try {
			conn.insert(updateSQL, queryParams);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
