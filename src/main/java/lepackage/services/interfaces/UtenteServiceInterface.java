package lepackage.services.interfaces;

import lepackage.models.dto.ResponseDTO;
import lepackage.models.dto.UtenteDTO;

public interface UtenteServiceInterface {

	public ResponseDTO findUtenteByUsername(UtenteDTO utenteDaCercare) throws Exception;
	
	public ResponseDTO findUtenteByEmailEPassword(UtenteDTO utenteDaCercare) throws Exception;
	
	public ResponseDTO findUtenteConMaterieDaUsername(UtenteDTO utenteDaCercare) throws Exception;
	
}
