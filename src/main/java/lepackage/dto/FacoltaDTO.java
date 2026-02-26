package lepackage.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lepackage.exceptions.BusinessException;
import lepackage.models.FacoltaEntity;
import lepackage.models.MateriaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacoltaDTO extends SuperDTO implements Serializable{

	private static final long serialVersionUID = 3051203338431526641L;
	private Integer id;
	private String nome;
	private List<String> materie;

	@Override
	public void verificaNonNullitaCampi() throws BusinessException {
		if (materie == null || materie.size() == 0) {
			throw new BusinessException("Campo materie vuoto o nullo a verificaNonNullitaCampi a FacoltaDTO.");
		}
	}
	
}
