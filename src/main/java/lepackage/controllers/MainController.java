package lepackage.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lepackage.dto.SuperDTO;
import lepackage.dto.UtenteDTO;
import lepackage.exceptions.BusinessException;
import lepackage.services.UtenteService;

@RestController
@RequestMapping("/user")
public class MainController {

	private UtenteService utenteService;

	public MainController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}

	@PostMapping("/findByUsername")
	public SuperDTO findUtenteByUsername(@RequestBody UtenteDTO utenteDaCercare) {
		try {
			SuperDTO dtoPerFrontend = utenteService.findUtenteByUsername(utenteDaCercare);
			return dtoPerFrontend;
		} catch (BusinessException e) {
			return new SuperDTO("Errore! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new SuperDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/tryLogin") 
	public SuperDTO tryLogin(@RequestBody UtenteDTO utenteDaLoggare) {
		try {
			SuperDTO dtoPerFrontend = utenteService.findUtenteByEmailEPassword(utenteDaLoggare);
			return dtoPerFrontend;
		} catch (BusinessException e) {
			return new SuperDTO("Errore! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new SuperDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}

}
