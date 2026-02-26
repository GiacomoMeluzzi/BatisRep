package lepackage.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lepackage.dao.VotoDao;
import lepackage.exceptions.BusinessException;
import lepackage.models.dto.ResponseDTO;
import lepackage.models.dto.VotoDTO;
import lepackage.models.entities.VotoEntity;
import lepackage.models.pojos.utilities.UtilityClass;

@Service
public class VotoService {
	
	VotoDao votoDao;
	UtilityClass<VotoDTO> utilityClass;
	
	public VotoService(VotoDao votoDao, UtilityClass<VotoDTO> utilityClass) {
		this.votoDao = votoDao;
		this.utilityClass = utilityClass;
	}

	public ResponseDTO selectVotiPerProfessoreEStudenteById(VotoDTO votoConUtenti) throws Exception {
		try {
			System.out.println("Entro in selectVotiPerProfessoreEStudenteById.");
			utilityClass.verificaOggettoNonNull(votoConUtenti);
			votoConUtenti.verificaNonNullitaCampi();
			System.out.println("Check completati a selectVotiPerProfessoreEStudenteById, chiamo DB.");
			
			VotoEntity votiRecuperati = votoDao.selectVotiPerProfessoreEStudenteById(votoConUtenti);
			ResponseDTO oggettoPerFrontEnd = new ResponseDTO("Voti trovati!", votiRecuperati, HttpStatus.OK);
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			System.out.println("BusinessException in VotoService " + e.getMessage());
			throw e;
		} catch(Exception e) {
			System.out.println("Exception in VotoService " + e.getMessage());
			throw e;
		}
	} 
	
}
