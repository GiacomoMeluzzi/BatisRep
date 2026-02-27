package lepackage.mappers;

import lepackage.models.dto.FacoltaUtenteDTO;
import lepackage.models.entities.FacoltaEntity;

public interface FacoltaMapper {


	FacoltaEntity findFacoltaConMaterieById(Integer facoltaId) throws Exception;
	

	Integer insertFacoltaEUtente(FacoltaUtenteDTO facoltaConUtenti) throws Exception;


	
}
