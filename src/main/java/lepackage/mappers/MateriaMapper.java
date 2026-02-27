package lepackage.mappers;

import lepackage.models.entities.MateriaEntity;
import lepackage.models.entities.UtenteEntity;

public interface MateriaMapper {
		
	MateriaEntity findMateriaConUtentiById(Integer materiaId) throws Exception;

	Integer insertMateriaEUtente(UtenteEntity utenteConMaterie) throws Exception;
		
}
