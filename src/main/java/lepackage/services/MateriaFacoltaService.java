package lepackage.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import lepackage.models.dto.FacoltaDTO;
import lepackage.models.dto.MateriaDTO;
import lepackage.models.dto.ResponseDTO;
import lepackage.exceptions.BusinessException;
import lepackage.dao.MateriaEFacoltaDao;
import lepackage.models.entities.FacoltaEntity;
import lepackage.models.entities.MateriaEntity;
import lepackage.models.pojos.utilities.ConvertitoreClass;
import lepackage.models.pojos.utilities.UtilityClass;

@Service
public class MateriaFacoltaService {

	private MateriaEFacoltaDao materiaFacoltaDao;
	private UtilityClass<Integer> utilityClass;

	public MateriaFacoltaService(MateriaEFacoltaDao materiaEFacoltaDao, UtilityClass<Integer> utilityClass) {
		this.materiaFacoltaDao = materiaEFacoltaDao;
		this.utilityClass = utilityClass;
	}

	public ResponseDTO findFacoltaConMaterieById(Integer facoltaDaControllareId) throws Exception {
		try {
			System.out.println("Chiamato findFacoltaConMaterieById a MateriaEFacoltaService.");
			utilityClass.verificaOggettoNonNull(facoltaDaControllareId);
			FacoltaEntity facoltaTrovata = materiaFacoltaDao.findFacoltaConMaterieById(facoltaDaControllareId);
			if (facoltaTrovata.getMaterie() == null || facoltaTrovata.getMaterie().size() == 0) {
				System.out.println("Nessuna materia trovata nella facoltà cercata.");
				throw new BusinessException(
						"Nessuna materia trovata nella facoltà cercata a MateriaEFacoltaService findFacoltaConMaterieById.");
			}
			FacoltaDTO facoltaPerFrontend = ConvertitoreClass.facoltaEntityToDTO(facoltaTrovata);
			System.out.println("findFacoltaConMaterieById restituisce oggetto a controller.");
			ResponseDTO oggettoPerFrontEnd = new ResponseDTO("Facoltà con materie trovata!", facoltaPerFrontend,
					HttpStatus.OK);
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			System.out.println("BusinessException a findFacoltaConMaterieById " + e.getMessage());
			throw new BusinessException(e.getMessage() + " a MateriaEFacoltaService findFacoltaConMaterieById");
		} catch (Exception e) {
			System.out.println("Exception a findFacoltaConMaterieById " + e.getMessage());
			throw e;
		}
	}

	public ResponseDTO findMateriaConUtenti(MateriaDTO materiaDaControllare) throws Exception {
		try {
			System.out.println("Chiamato findUtenteConMaterie a MateriaEFacoltaService.");
			utilityClass.verificaOggettoNonNull(materiaDaControllare.getId());
			MateriaEntity materiaConUtentiTrovata = materiaFacoltaDao.findMateriaConUtentiById(materiaDaControllare.getId());
			MateriaDTO materiaPerFrontEnd = ConvertitoreClass.materiaEntityToDTO(materiaConUtentiTrovata);
			ResponseDTO oggettoPerFrontEnd = new ResponseDTO("Materia con utenti trovata!", materiaPerFrontEnd, HttpStatus.OK);
			return oggettoPerFrontEnd;
		} catch (Exception e) {
			System.out.println("Exception a findUtenteConMaterie " + e.getMessage());
			throw e;
		}
	}

}
