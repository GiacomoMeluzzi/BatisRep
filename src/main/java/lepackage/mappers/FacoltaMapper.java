package lepackage.mappers;

import lepackage.models.entities.FacoltaEntity;

public interface FacoltaMapper {


	FacoltaEntity findFacoltaConMaterieById(Integer facoltaId) throws Exception;	

	Integer insertFacoltaEUtente(FacoltaEntity facoltaConUtenti) throws Exception;


	
}
