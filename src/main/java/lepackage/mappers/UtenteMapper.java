package lepackage.mappers;

import lepackage.models.UtenteEntity;

public interface UtenteMapper {

	public UtenteEntity findUtenteByUsername(String username);
	
}
