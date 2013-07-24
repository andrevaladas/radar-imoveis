package com.chronosystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chronosystems.entity.Imovel;
import com.chronosystems.service.ImovelService;

@Controller
@RequestMapping
public class WebController {

	@Autowired
	private ImovelService imovelService;
	
	@RequestMapping("/imoveis")
	public String imoveis(final ModelMap model) {
		final List<Imovel> imoveis = imovelService.findAll();
		model.addAttribute("imovelList", imoveis);
		return "index";
	}

	@RequestMapping("/page/{page}")
	public String page(@PathVariable("page") final String page, final ModelMap model) {
		return page;
	}
}