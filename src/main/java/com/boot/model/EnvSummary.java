package com.boot.model;

import java.util.List;
import java.util.Map;

public class EnvSummary {

	private int totalServers;
	private int totalEnvironments;
	private Map<String,List<Serverinfo>> serverDetails;
	
	public int getTotalServers() {
		return totalServers;
	}
	public void setTotalServers(int totalServers) {
		this.totalServers = totalServers;
	}
	public int getTotalEnvironments() {
		return totalEnvironments;
	}
	public void setTotalEnvironments(int totalEnvironments) {
		this.totalEnvironments = totalEnvironments;
	}
	public Map<String,List<Serverinfo>> getServerDetails() {
		return serverDetails;
	}
	public void setServerDetails(Map<String,List<Serverinfo>> serverDetails) {
		this.serverDetails = serverDetails;
	}
}
