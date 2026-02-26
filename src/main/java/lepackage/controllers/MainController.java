package lepackage.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lepackage.dto.FacoltaDTO;
import lepackage.dto.ResponseDTO;
import lepackage.dto.UtenteDTO;
import lepackage.exceptions.BusinessException;
import lepackage.services.MateriaEFacoltaService;
import lepackage.services.UtenteService;

@RestController
@RequestMapping("/user")
public class MainController {

	private UtenteService utenteService;
	private MateriaEFacoltaService facoltaMateriaService;

	public MainController(UtenteService utenteService, MateriaEFacoltaService facoltaMateriaService) {
		this.utenteService = utenteService;
		this.facoltaMateriaService = facoltaMateriaService;
	}

	@PostMapping("/findByUsername")
	public ResponseDTO findUtenteByUsername(@RequestBody UtenteDTO utenteDaCercare) {
		try {
			ResponseDTO dtoPerFrontend = utenteService.findUtenteByUsername(utenteDaCercare);
			return dtoPerFrontend;
		} catch (BusinessException e) {
			return new ResponseDTO("Errore! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/tryLogin") 
	public ResponseDTO tryLogin(@RequestBody UtenteDTO utenteDaLoggare) {
		try {
			ResponseDTO dtoPerFrontend = utenteService.findUtenteByEmailEPassword(utenteDaLoggare);
			return dtoPerFrontend;
		} catch (BusinessException e) {
			return new ResponseDTO("Errore! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/getMaterieUtente")
	public ResponseDTO trovaMaterieUtente(@RequestBody UtenteDTO utenteDaTrovareConMaterie) {
		try {
			ResponseDTO dtoPerFrontend = utenteService.findUtenteConMaterieDaUsername(utenteDaTrovareConMaterie);
			return dtoPerFrontend;
		} catch (BusinessException e) {
			return new ResponseDTO("Errore! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/getFacolta")
	public ResponseDTO trovaFacoltaConMaterie(@RequestBody FacoltaDTO facoltaDaTrovareConMaterie) {
		try {
			ResponseDTO dtoPerFrontend = facoltaMateriaService.findFacoltaConMaterieById(facoltaDaTrovareConMaterie.getId());
			return dtoPerFrontend;
		} catch (BusinessException e) {
			return new ResponseDTO("Errore! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/registraUtente")
	public ResponseDTO registraNuovoUtente(@RequestBody UtenteDTO utenteDaRegistrare) {
		try {
			ResponseDTO dtoPerFrontend = null;
			return dtoPerFrontend;
		} 
//		catch (BusinessException e) {
//			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
//		}	
		catch (Exception e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}
	

	}

