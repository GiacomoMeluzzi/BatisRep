package lepackage.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lepackage.exceptions.BusinessException;
import lepackage.models.dto.MateriaDTO;
import lepackage.models.dto.ResponseDTO;
import lepackage.services.interfaces.MateriaServiceInterface;

@RestController
@RequestMapping("/materia")
public class MateriaController {
	
	MateriaServiceInterface materiaService;
	
	public MateriaController (MateriaServiceInterface materiaService) {
		this.materiaService = materiaService;
	}
	
	@PostMapping("/trovaMateria")
	public ResponseDTO trovaMateriaConUtenti(@RequestBody MateriaDTO materiaDaCercare) {
		try {
			ResponseDTO dtoPerFrontend = materiaService.findMateriaConUtenti(materiaDaCercare);
			return dtoPerFrontend;
		} catch (BusinessException e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseDTO("Errore generico! " + e.getMessage(), null, HttpStatus.BAD_REQUEST);
		} 
	}
}
