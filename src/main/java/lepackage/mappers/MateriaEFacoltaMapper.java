package lepackage.mappers;

import lepackage.dto.FacoltaUtenteDTO;
import lepackage.dto.UtenteMateriaDTO;
import lepackage.models.FacoltaEntity;
import lepackage.models.MateriaEntity;
import lepackage.models.UtenteEntity;

public interface MateriaEFacoltaMapper {
	
	FacoltaEntity findFacoltaConMaterieById(Integer facoltaId) throws Exception;
	
	MateriaEntity findMateriaConUtentiById(Integer materiaId) throws Exception;
	
	Integer insertMateriaEUtente(UtenteMateriaDTO utenteConMaterie) throws Exception;
	
	Integer insertFacoltaEUtente(FacoltaUtenteDTO facoltaConUtenti) throws Exception;
	
}
