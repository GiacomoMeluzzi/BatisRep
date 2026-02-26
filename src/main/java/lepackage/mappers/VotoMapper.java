package lepackage.mappers;

import lepackage.models.dto.VotoDTO;
import lepackage.models.entities.VotoEntity;

public interface VotoMapper {
	
	public VotoEntity selectVotiPerProfessoreEStudenteById(VotoDTO dtoConUtentiDaCercare) throws Exception;
	
}
