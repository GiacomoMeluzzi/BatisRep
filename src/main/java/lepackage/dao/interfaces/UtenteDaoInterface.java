package lepackage.dao.interfaces;

import lepackage.models.dto.UtenteDTO;
import lepackage.models.entities.UtenteEntity;

public interface UtenteDaoInterface {
	
	UtenteEntity findUtenteByUsername(String usernameInEntrata) throws Exception;
	
	UtenteEntity findUtenteByEmailEPassword(String emailInEntrata, String passwordInEntrata) throws Exception;
	
	UtenteEntity findUtenteByEmailEPasswordJoinRuolo(String emailInEntrata, String passwordInEntrata) throws Exception;
	
	UtenteEntity findUtenteConMaterieDaUsername(String usernameInEntrata) throws Exception;
	
	int inserisciNuovoUtente(UtenteDTO nuovoUtente) throws Exception;
	
}
