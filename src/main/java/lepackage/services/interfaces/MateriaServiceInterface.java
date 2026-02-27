package lepackage.services.interfaces;

import lepackage.models.dto.MateriaDTO;
import lepackage.models.dto.ResponseDTO;

public interface MateriaServiceInterface {

	public ResponseDTO findMateriaConUtenti(MateriaDTO materiaDaControllare) throws Exception;
	
}
