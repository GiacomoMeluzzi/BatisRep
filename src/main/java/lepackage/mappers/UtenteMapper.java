package lepackage.mappers;

import lepackage.models.UtenteEntity;

public interface UtenteMapper {

	public UtenteEntity findUtenteByUsername(String username) throws Exception;
	
	public UtenteEntity findUtenteByEmailEPassword(String email, String password) throws Exception;
	
	public UtenteEntity findUtenteByEmailEPasswordJoinRuolo(String email, String password) throws Exception;
	
}
