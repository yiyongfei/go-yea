package com.team.goyea.web.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController  
@RequestMapping("/json")
public class JsonController {
	
	@RequestMapping("/{id}")
	public Map<String, Object> view(@PathVariable("id") Long id) {
		Map<String, Object> user = new HashMap<String, Object>();
		user.put("id", id);
		user.put("name", "admin");
		return user;
	}
}
