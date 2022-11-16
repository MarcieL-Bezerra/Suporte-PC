package br.com.marciel.sosnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestMarcielController {

	
	@GetMapping("/pessoa")
	public String inicial(Model model) {
		
		model.addAttribute("funcionarios", "Olá");
		return "inicial.html";
	}

}
