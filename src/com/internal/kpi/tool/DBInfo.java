package com.internal.kpi.tool;

public class DBInfo {
	private final String url;
	private final String dbName;
	private final String driver;
	private final String userName;
	private final String password;
	
	public DBInfo(String url, String dbName, String driver, String userName, String password) {
		super();
		this.url = url;
		this.dbName = dbName;
		this.driver = driver;
		this.userName = userName;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public String getDbName() {
		return dbName;
	}

	public String getDriver() {
		return driver;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	
}
