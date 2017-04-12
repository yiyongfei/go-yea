package com.team.goyea.web.ftl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/index.html")
    public String index(ModelMap model) {
        logger.info("进入首页页面！");
        return "index";
    }
	@RequestMapping("/unauthorized.html")
    public String unauthorized(ModelMap model) {
        return "unauthorized";
    }
	
}
