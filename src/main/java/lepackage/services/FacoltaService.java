package lepackage.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import lepackage.models.dto.FacoltaDTO;
import lepackage.models.dto.ResponseDTO;
import lepackage.exceptions.BusinessException;
import lepackage.dao.interfaces.FacoltaDaoInterface;
import lepackage.models.entities.FacoltaEntity;
import lepackage.models.pojos.utilities.Convertitore;
import lepackage.models.pojos.utilities.UtilityClass;
import lepackage.services.interfaces.FacoltaServiceInterface;

@Service
public class FacoltaService implements FacoltaServiceInterface {

	private FacoltaDaoInterface facoltaDao;
	private UtilityClass<Integer> utilityClass;

	public FacoltaService(FacoltaDaoInterface facoltaDao, UtilityClass<Integer> utilityClass) {
		this.facoltaDao = facoltaDao;
		this.utilityClass = utilityClass;
	}

	public ResponseDTO findFacoltaConMaterieById(Integer facoltaDaControllareId) throws Exception {
		try {
			System.out.println("Chiamato findFacoltaConMaterieById a MateriaEFacoltaService.");
			utilityClass.verificaOggettoNonNull(facoltaDaControllareId);
			FacoltaEntity facoltaTrovata = facoltaDao.findFacoltaConMaterieById(facoltaDaControllareId);
			if (facoltaTrovata.getMaterie() == null || facoltaTrovata.getMaterie().size() == 0) {
				System.out.println("Nessuna materia trovata nella facoltà cercata.");
				throw new BusinessException(
						"Nessuna materia trovata nella facoltà cercata a MateriaEFacoltaService findFacoltaConMaterieById.");
			}
			FacoltaDTO facoltaPerFrontend = Convertitore.facoltaEntityToDTO(facoltaTrovata);
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

}
