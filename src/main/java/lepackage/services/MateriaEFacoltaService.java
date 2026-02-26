package lepackage.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lepackage.dao.MateriaEFacoltaDao;
import lepackage.dto.FacoltaDTO;
import lepackage.dto.ResponseDTO;
import lepackage.exceptions.BusinessException;
import lepackage.models.FacoltaEntity;
import lepackage.utilities.ConvertitoreClass;
import lepackage.utilities.UtilityClass;

@Service
public class MateriaEFacoltaService {

	private MateriaEFacoltaDao materiaFacoltaDao;
	private UtilityClass<Integer> utilityClass;

	public MateriaEFacoltaService(MateriaEFacoltaDao materiaEFacoltaDao, UtilityClass<Integer> utilityClass) {
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
			FacoltaDTO facoltaPerFrontend = ConvertitoreClass.facoltaDTOtoEntity(facoltaTrovata);
			System.out.println("findFacoltaConMaterieById restituisce oggetto a controller.");
			ResponseDTO oggettoPerFrontEnd = new ResponseDTO("Facoltà con materie trovata!", facoltaPerFrontend,
					HttpStatus.OK);
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage() + " a MateriaEFacoltaService findFacoltaConMaterieById");
		} catch (Exception e) {
			throw e;
		}
	}

}
