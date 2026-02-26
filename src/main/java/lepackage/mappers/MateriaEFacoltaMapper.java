package lepackage.mappers;

import lepackage.dto.FacoltaUtenteDTO;
import lepackage.dto.UtenteMateriaDTO;
import lepackage.models.FacoltaEntity;

public interface MateriaEFacoltaMapper {
	
	FacoltaEntity findFacoltaConMaterieById(Integer facoltaId) throws Exception;
	
	Integer insertMateriaEUtente(UtenteMateriaDTO utenteConMaterie) throws Exception;
	
	Integer insertFacoltaEUtente(FacoltaUtenteDTO facoltaConUtenti) throws Exception;
	
}
