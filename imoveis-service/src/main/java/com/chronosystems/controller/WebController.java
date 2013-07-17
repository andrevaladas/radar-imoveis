package com.chronosystems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class WebController {

	@RequestMapping("/page/{page}")
	public String page(@PathVariable("page") final String page, final ModelMap model) {
		return page;
	}
}