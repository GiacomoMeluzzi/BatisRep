package lepackage.services.interfaces;

import lepackage.models.dto.ResponseDTO;
import lepackage.models.dto.VotoDTO;

public interface VotoServiceInterface {

	public ResponseDTO selectVotiPerProfessoreEStudenteById(VotoDTO votoConUtenti) throws Exception;
	
}
