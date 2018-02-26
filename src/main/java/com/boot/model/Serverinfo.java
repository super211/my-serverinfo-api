package com.boot.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Serverinfo {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String serverIp;
	String hostName;
	String serverCategory;
	String envCategory;
	String environmentName;
	String productCategory;
	String productName;
	String developmentStream;
	String assignedTo;
	String databaseEndpoint;

	String programName;
	String subDepartment;
	String comments;
	Date lastUpdated;

	public Serverinfo(Long id, String serverIp, String hostName, String subDepartment, String serverCategory,
			String envCategory, String environmentName, String productCategory, String assignedTo, String databaseEndPoint,
			String productName,	String developmentStream) {
		super();
		this.id = id;
		this.serverIp = serverIp;
		this.hostName = hostName;
		this.subDepartment = subDepartment;
		this.serverCategory = serverCategory;
		this.envCategory = envCategory;
		this.environmentName = environmentName;
		this.productCategory = productCategory;
		this.productName = productName;
		this.developmentStream = developmentStream;
		this.assignedTo = assignedTo;
		this.databaseEndpoint = databaseEndPoint;
		
		Calendar today = Calendar.getInstance();
		today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
		this.lastUpdated = today.getTime();
	}
	
	public Serverinfo(Long id, String serverIp, String hostName, String subDepartment, String serverCategory,
			String envCategory, String environmentName, String productCategory, String assignedTo, String databaseEndPoint,
			String productName,	String developmentStream, String programName, String comments, Date lastUpdated) {
		super();
		this.id = id;
		this.serverIp = serverIp;
		this.hostName = hostName;
		this.subDepartment = subDepartment;
		this.serverCategory = serverCategory;
		this.envCategory = envCategory;
		this.environmentName = environmentName;
		this.productCategory = productCategory;
		this.productName = productName;
		this.developmentStream = developmentStream;
		this.assignedTo = assignedTo;
		this.databaseEndpoint = databaseEndPoint;
		this.programName = programName;
		this.comments = comments;
		this.lastUpdated = lastUpdated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(String subDepartment) {
		this.subDepartment = subDepartment;
	}

	public String getServerCategory() {
		return serverCategory;
	}

	public void setServerCategory(String serverCategory) {
		this.serverCategory = serverCategory;
	}

	public String getEnvCategory() {
		return envCategory;
	}

	public void setEnvCategory(String envCategory) {
		this.envCategory = envCategory;
	}

	public String getEnvironmentName() {
		return environmentName;
	}

	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDevelopmentStream() {
		return developmentStream;
	}

	public void setDevelopmentStream(String developmentStream) {
		this.developmentStream = developmentStream;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getDatabaseEndpoint() {
		return databaseEndpoint;
	}

	public void setDatabaseEndpoint(String databaseEndpoint) {
		this.databaseEndpoint = databaseEndpoint;
	}
	
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Serverinfo() { }
	
	

}
