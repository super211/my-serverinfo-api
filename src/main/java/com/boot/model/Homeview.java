package com.boot.model;

import java.util.HashMap;
import java.util.Map;

import com.boot.model.EnvSummary;

public class Homeview {

	private Map<String, EnvSummary> EnvInfos = new HashMap<String,EnvSummary>();
	private String pageTitle;;
	private int totalServersCount;

	public Map<String, EnvSummary> getEnvInfos() {
		return EnvInfos;
	}

	public void setEnvInfos(Map<String, EnvSummary> envInfos) {
		EnvInfos = envInfos;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public int getTotalServersCount() {
		return totalServersCount;
	}

	public void setTotalServersCount(int totalServersCount) {
		this.totalServersCount = totalServersCount;
	}


}
