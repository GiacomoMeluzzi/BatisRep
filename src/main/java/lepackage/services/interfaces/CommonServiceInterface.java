package lepackage.services.interfaces;

import lepackage.models.dto.ResponseDTO;
import lepackage.models.dto.UtenteDTO;

public interface CommonServiceInterface {

	public ResponseDTO tryRegistrazioneUtente(UtenteDTO utenteDaRegistrare) throws Exception;
	
}
