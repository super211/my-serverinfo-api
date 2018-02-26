package com.boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Serverinfo;
import com.boot.repository.ServerinfoRepository;

@RestController
@RequestMapping("serverinfo")
public class ServerinfoController {

	private static final Logger log = LoggerFactory.getLogger(ServerinfoController.class);
	
	@Autowired
	private ServerinfoRepository serverinfoRepository;

	@GetMapping(value = "")
	public List<Serverinfo> list() {
		return serverinfoRepository.findAll();
	}

	@PostMapping(value = "")
	public Serverinfo create(@RequestBody Serverinfo serverinfo) {
		return serverinfoRepository.saveAndFlush(serverinfo);
	}

	@GetMapping(value = "/{id}")
	public Serverinfo get(@PathVariable Long id) {
		return serverinfoRepository.findOne(id);
	}

	@PutMapping(value = "/{id}")
	public Serverinfo update(@PathVariable Long id, @RequestBody Serverinfo serverinfo) {
		Serverinfo existingServerinfo = serverinfoRepository.findOne(id);
		BeanUtils.copyProperties(serverinfo, existingServerinfo);
		return serverinfoRepository.saveAndFlush(existingServerinfo);
	}

	@DeleteMapping(value = "/{id}")
	@CrossOrigin("*")
	public Serverinfo delete(@PathVariable Long id) {
		Serverinfo existingServerinfo = serverinfoRepository.findOne(id);
		serverinfoRepository.delete(existingServerinfo);
		return existingServerinfo;
	}
	
	@PostMapping(value = "/bulkinsert")
	public List<Serverinfo> create(@RequestBody List<Serverinfo> serverinfos) {
		List<Serverinfo> srvinfos = new ArrayList<Serverinfo>();
		
		serverinfos.forEach((serverinfo)->{
			
		try {
		 srvinfos.add(serverinfoRepository.saveAndFlush(serverinfo));
		}catch(Exception exc) {
//			ObjectMapper mapper = new ObjectMapper();
			log.error(String.format("Error Adding Info : {0},{1},{2}", serverinfo.getEnvCategory(), serverinfo.getEnvironmentName(), serverinfo.getHostName()),exc);
		}
		});
		return(srvinfos);
	}
}
