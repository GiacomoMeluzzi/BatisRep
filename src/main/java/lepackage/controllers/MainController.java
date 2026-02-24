package lepackage.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lepackage.models.UtenteEntity;
import lepackage.services.UtenteService;

@RestController
@RequestMapping("/user")
public class MainController {

	private UtenteService utenteService;
	
	public MainController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}
	
	@GetMapping("/findByUsername")
	public UtenteEntity findUtenteByUsername(@RequestParam String username) {
		System.out.println("I was pinged");
		return utenteService.findUtenteByUsername(username);
	}
	
}
