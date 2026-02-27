package lepackage.dao.interfaces;

import lepackage.models.entities.MateriaEntity;
import lepackage.models.entities.UtenteEntity;

public interface MateriaDaoInterface {

	MateriaEntity findMateriaConUtentiById(Integer materiaId) throws Exception;
	
	int insertMateriaEUtente(UtenteEntity utenteConMaterie) throws Exception;
	
}
