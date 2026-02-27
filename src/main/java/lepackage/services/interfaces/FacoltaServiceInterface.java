package lepackage.services.interfaces;

import lepackage.models.dto.ResponseDTO;

public interface FacoltaServiceInterface {

	public ResponseDTO findFacoltaConMaterieById(Integer facoltaDaControllareId) throws Exception;
	
}
