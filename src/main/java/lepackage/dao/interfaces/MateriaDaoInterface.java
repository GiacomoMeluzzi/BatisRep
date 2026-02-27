package lepackage.dao.interfaces;

import lepackage.models.dto.UtenteMateriaDTO;
import lepackage.models.entities.MateriaEntity;

public interface MateriaDaoInterface {

	public MateriaEntity findMateriaConUtentiById(Integer materiaId) throws Exception;
	
	public Integer insertMateriaEUtente(UtenteMateriaDTO utenteConMaterie) throws Exception;
	
}
