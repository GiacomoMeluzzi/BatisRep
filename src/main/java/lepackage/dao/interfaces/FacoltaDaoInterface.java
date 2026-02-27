package lepackage.dao.interfaces;

import lepackage.models.entities.FacoltaEntity;

public interface FacoltaDaoInterface {

	FacoltaEntity findFacoltaConMaterieById(Integer facoltaId) throws Exception;
	
	int insertFacoltaEUtente(FacoltaEntity facoltaConUtenti) throws Exception;
	
}
