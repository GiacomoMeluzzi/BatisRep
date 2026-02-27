package lepackage.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lepackage.models.dto.ResponseDTO;
import lepackage.models.dto.UtenteDTO;
import lepackage.exceptions.BusinessException;
import lepackage.services.interfaces.CommonServiceInterface;
import lepackage.services.interfaces.UtenteServiceInterface;

@RestController
@RequestMapping("/user")
public class UtenteController {

	private UtenteServiceInterface utenteService;
	private CommonServiceInterface commonService;

	public UtenteController(UtenteServiceInterface utenteService, CommonServiceInterface commonService) {
		this.utenteService = utenteService;
		this.commonService = commonService;
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
			ResponseDTO responseDaService = utenteService.findUtenteConMaterieDaUsername(utenteDaTrovareConMaterie);
			return new ResponseDTO("Utente registrato!", responseDaService.getDtoForFrontEnd(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseDTO("Errore! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/registraUtente")
	public ResponseDTO registraNuovoUtente(@RequestBody UtenteDTO utenteDaRegistrare) {
		try {
			ResponseDTO dtoPerFrontend = commonService.tryRegistrazioneUtente(utenteDaRegistrare);
			return new ResponseDTO("Utente registrato!", dtoPerFrontend.getDtoForFrontEnd(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}

}
