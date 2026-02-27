package lepackage.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lepackage.exceptions.BusinessException;
import lepackage.models.dto.FacoltaDTO;
import lepackage.models.dto.MateriaDTO;
import lepackage.models.dto.ResponseDTO;
import lepackage.services.FacoltaService;
import lepackage.services.interfaces.FacoltaServiceInterface;

@RestController
@RequestMapping("/facolta")
public class FacoltaController {
	
	private FacoltaServiceInterface facoltaService;
	
	public FacoltaController (FacoltaServiceInterface facoltaService) {
		this.facoltaService = facoltaService;
	}
	
	@PostMapping("/getFacolta")
	public ResponseDTO trovaFacoltaConMaterie(@RequestBody FacoltaDTO facoltaDaTrovareConMaterie) {
		try {
			ResponseDTO dtoPerFrontend = facoltaService.findFacoltaConMaterieById(facoltaDaTrovareConMaterie.getId());
			return dtoPerFrontend;
		} catch (BusinessException e) {
			return new ResponseDTO("Errore! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}	

}
