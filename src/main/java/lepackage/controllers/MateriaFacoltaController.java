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
import lepackage.services.MateriaFacoltaService;

@RestController
@RequestMapping("/materia")
public class MateriaFacoltaController {
	
	private MateriaFacoltaService facoltaMateriaService;
	
	public MateriaFacoltaController (MateriaFacoltaService facoltaMateriaService) {
		this.facoltaMateriaService = facoltaMateriaService;
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
	
	@PostMapping("/trovaMateria")
	public ResponseDTO trovaMateriaConUtenti(@RequestBody MateriaDTO materiaDaCercare) {
		try {
			ResponseDTO dtoPerFrontend = facoltaMateriaService.findMateriaConUtenti(materiaDaCercare);
			return dtoPerFrontend;
		} catch (BusinessException e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} 
	}

}
