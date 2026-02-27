package lepackage.mappers;

import lepackage.models.dto.FacoltaUtenteDTO;
import lepackage.models.dto.UtenteMateriaDTO;
import lepackage.models.entities.FacoltaEntity;
import lepackage.models.entities.MateriaEntity;

public interface MateriaMapper {
		
	MateriaEntity findMateriaConUtentiById(Integer materiaId) throws Exception;
	//mettere entities
	Integer insertMateriaEUtente(UtenteMateriaDTO utenteConMaterie) throws Exception;
		
}
