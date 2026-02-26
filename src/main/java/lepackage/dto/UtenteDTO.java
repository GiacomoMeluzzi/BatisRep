package lepackage.dto;

import java.util.ArrayList;
import java.util.List;

import lepackage.exceptions.BusinessException;
import lepackage.models.MateriaEntity;
import lepackage.models.UtenteEntity;
import lepackage.varie.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtenteDTO extends SuperDTO {

	private Integer id;
	private String username;
	private String email;
	private String password;
	private Ruolo ruolo;
	private List<String> materie;
	private Integer facoltaId;

	@Override
	public void verificaNonNullitaCampi() throws BusinessException {
		if (materie == null || materie.size() == 0) {
			throw new BusinessException("Campo materie vuoto a verificaNonNullitaCampi a UtenteDTO.");
		}

	}

}
