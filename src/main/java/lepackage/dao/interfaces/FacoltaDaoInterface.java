package lepackage.dao.interfaces;

import lepackage.models.dto.FacoltaUtenteDTO;
import lepackage.models.entities.FacoltaEntity;

public interface FacoltaDaoInterface {

	public FacoltaEntity findFacoltaConMaterieById(Integer facoltaId) throws Exception;
	
	public Integer insertFacoltaEUtente(FacoltaUtenteDTO facoltaConUtenti) throws Exception;
	
}
