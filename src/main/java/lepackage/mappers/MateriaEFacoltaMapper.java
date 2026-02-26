package lepackage.mappers;

import lepackage.models.dto.FacoltaUtenteDTO;
import lepackage.models.dto.UtenteMateriaDTO;
import lepackage.models.entities.FacoltaEntity;
import lepackage.models.entities.MateriaEntity;

public interface MateriaEFacoltaMapper {
	
	FacoltaEntity findFacoltaConMaterieById(Integer facoltaId) throws Exception;
	
	MateriaEntity findMateriaConUtentiById(Integer materiaId) throws Exception;
	
	Integer insertMateriaEUtente(UtenteMateriaDTO utenteConMaterie) throws Exception;
	
	Integer insertFacoltaEUtente(FacoltaUtenteDTO facoltaConUtenti) throws Exception;
	
}
