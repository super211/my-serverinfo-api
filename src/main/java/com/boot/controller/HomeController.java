package com.boot.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Homeview;
import com.boot.model.Serverinfo;
import com.boot.repository.ServerinfoRepository;
import com.boot.model.EnvSummary;

@RestController
@RequestMapping("home")
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ServerinfoRepository serverinfoRepository;

	@GetMapping(value = "")
	public Homeview home() {
		Homeview _view = new Homeview();
		_view.setPageTitle("Server Info, home page!");
		
	   List<String> envCats=serverinfoRepository.findDistinctEnvCategories();
	   Map<String,EnvSummary> lst = new HashMap<String,EnvSummary>();
		try {
			_view.setTotalServersCount(serverinfoRepository.getServersCount());
			envCats.forEach((envc) -> {
				EnvSummary s = new EnvSummary();
				s.setTotalServers(serverinfoRepository.getServersCountByEnvCategory(envc));
				s.setTotalEnvironments(serverinfoRepository.getEnvCountByEnvCategory(envc));
				
				try {
					Map<String,List<Serverinfo>> envServers = new HashMap<String,List<Serverinfo>>();
					List<String> envList = serverinfoRepository.getEnvByEnvCat(envc);
					envList.forEach((env)->{
//						List<Serverinfo> srvrs = serverinfoRepository.getServersByEnv(env);
//						envServers.put(env, srvrs); //Only put summary here. Fetch details only on demand
						envServers.put(env, null);
					});
			    s.setServerDetails(envServers);
				}catch(Exception exc1) {
					log.error("Unable to fetch server details", exc1);
				}
				
				lst.put(envc, s);
			});
		} catch (Exception exc) {
			log.error(exc.getMessage(), exc);
		}
		_view.setEnvInfos(lst);
		return _view;
	}
	
	@GetMapping(value="/{envName}")
	public List<Serverinfo> envdetails(@PathVariable String envName) {
		try {
			List<Serverinfo> srvrs = serverinfoRepository.getServersByEnv(envName);
			return srvrs;
		}catch(Exception exc1) {
			log.error("Unable to fetch server details", exc1);
			return null;
		}
	}

}
