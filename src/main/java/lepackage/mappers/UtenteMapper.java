package lepackage.mappers;

import lepackage.dto.UtenteDTO;
import lepackage.models.entities.UtenteEntity;

public interface UtenteMapper {

	UtenteEntity findUtenteByUsername(String username) throws Exception;
	
	UtenteEntity findUtenteByEmailEPassword(String email, String password) throws Exception;
	
	UtenteEntity findUtenteByEmailEPasswordJoinRuolo(String email, String password) throws Exception;
	
	UtenteEntity findUtenteConMaterieDaUsername(String username) throws Exception;
	
	Integer inserisciNuovoUtente(UtenteDTO nuovoUtente) throws Exception;
}
