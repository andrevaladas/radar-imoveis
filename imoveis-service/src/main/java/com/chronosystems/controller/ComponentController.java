package com.chronosystems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ComponentController {

	@RequestMapping("/component/{page}")
	public String component(@PathVariable("page") final String page) {
		return "component/"+page;
	}
}