package lepackage.mappers;

import lepackage.models.FacoltaEntity;

public interface MateriaEFacoltaMapper {
	
	public FacoltaEntity findFacoltaConMaterieById(Integer facoltaId) throws Exception;
	
}
