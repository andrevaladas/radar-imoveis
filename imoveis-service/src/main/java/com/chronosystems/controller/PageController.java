package com.chronosystems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/page/{page}")
	public String page(@PathVariable("page") final String page) {
		return page;
	}
}