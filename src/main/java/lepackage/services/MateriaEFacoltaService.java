package lepackage.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lepackage.dao.MateriaEFacoltaDao;
import lepackage.dto.FacoltaDTO;
import lepackage.dto.ResponseDTO;
import lepackage.exceptions.BusinessException;
import lepackage.models.FacoltaEntity;

@Service
public class MateriaEFacoltaService {

	private MateriaEFacoltaDao materiaFacoltaDao;

	public MateriaEFacoltaService(MateriaEFacoltaDao materiaEFacoltaDao) {
		this.materiaFacoltaDao = materiaEFacoltaDao;
	}

	public ResponseDTO findFacoltaConMaterieById(String facoltaDaControllareId) throws Exception {
		try {
			System.out.println("Chiamato findFacoltaConMaterieById a MateriaEFacoltaService.");
			FacoltaEntity facoltaTrovata = materiaFacoltaDao.findFacoltaConMaterieById(facoltaDaControllareId);
			if (facoltaTrovata.getMaterie() == null || facoltaTrovata.getMaterie().size() == 0) {
				System.out.println("Nessuna materia trovata nella facoltà cercata.");
				throw new BusinessException(
						"Nessuna materia trovata nella facoltà cercata a MateriaEFacoltaService findFacoltaConMaterieById.");
			}
			FacoltaDTO facoltaPerFrontend = new FacoltaDTO(facoltaTrovata);
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
