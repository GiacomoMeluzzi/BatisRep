package lepackage.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lepackage.dao.interfaces.MateriaDaoInterface;
import lepackage.models.dto.MateriaDTO;
import lepackage.models.dto.ResponseDTO;
import lepackage.models.entities.MateriaEntity;
import lepackage.models.pojos.utilities.Convertitore;
import lepackage.models.pojos.utilities.UtilityClass;
import lepackage.services.interfaces.MateriaServiceInterface;

@Service
public class MateriaService implements MateriaServiceInterface {
	
	private MateriaDaoInterface materiaDao;
	private UtilityClass<MateriaDTO> utilityClass;
	
	public MateriaService(MateriaDaoInterface materiaDao, UtilityClass<MateriaDTO> utilityClass) {
		this.materiaDao = materiaDao;
		this.utilityClass = utilityClass;
	}

	public ResponseDTO findMateriaConUtenti(MateriaDTO materiaDaControllare) throws Exception {
		try {
			System.out.println("Chiamato findUtenteConMaterie a MateriaEFacoltaService.");
			utilityClass.verificaOggettoNonNull(materiaDaControllare);
			MateriaEntity materiaConUtentiTrovata = materiaDao.findMateriaConUtentiById(materiaDaControllare.getId());
			MateriaDTO materiaPerFrontEnd = Convertitore.materiaEntityToDTO(materiaConUtentiTrovata);
			ResponseDTO oggettoPerFrontEnd = new ResponseDTO("Materia con utenti trovata!", materiaPerFrontEnd, HttpStatus.OK);
			return oggettoPerFrontEnd;
		} catch (Exception e) {
			System.out.println("Exception a findUtenteConMaterie " + e.getMessage());
			throw e;
		}
	}

	
}
