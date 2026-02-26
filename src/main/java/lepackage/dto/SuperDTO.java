package lepackage.dto;

import lepackage.exceptions.BusinessException;

public abstract class SuperDTO {
	
	public abstract void verificaNonNullitaCampi() throws BusinessException;
	
}
