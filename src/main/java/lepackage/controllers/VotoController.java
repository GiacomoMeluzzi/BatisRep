package lepackage.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lepackage.exceptions.BusinessException;
import lepackage.models.dto.ResponseDTO;
import lepackage.models.dto.VotoDTO;
import lepackage.services.VotoService;

@RestController
@RequestMapping("/voto")
public class VotoController {
	
	private VotoService votoService;
	
	public VotoController(VotoService votoService) {
		this.votoService = votoService;
	}
	
	@PostMapping("/votiConUtenti")
	public ResponseDTO trovaVotiConUtenti(VotoDTO votoDaCercare)  {
		try {
			ResponseDTO oggettoPerFrontEnd = votoService.selectVotiPerProfessoreEStudenteById(votoDaCercare);
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			System.out.println("BusinessException a votoController " + e.getMessage());
			return new ResponseDTO("BusinessException a votoController " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			System.out.println("Exception a votoController " + e.getMessage());
			return new ResponseDTO("Exception a votoController " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}
	
}
