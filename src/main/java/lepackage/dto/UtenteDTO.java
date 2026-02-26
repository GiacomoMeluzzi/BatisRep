package lepackage.dto;

import java.util.List;

import lepackage.exceptions.BusinessException;
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
	private String ruolo;
	private List<String> materie;
	private List<Integer> materieId;
	private Integer facoltaId;
	
	public UtenteDTO (String username, String email, String ruolo) {
		this.username = username;
		this.email = email;
		this.ruolo = ruolo;
	}

	@Override
	public void verificaNonNullitaCampi() throws BusinessException {
		if (materie == null || materie.size() == 0) {
			throw new BusinessException("Campo materie vuoto a verificaNonNullitaCampi a UtenteDTO.");
		}
	}

	public void verificaNonNullitaCampiPerRegister() throws BusinessException {
		if (materieId == null || materieId.size() == 0) {
			throw new BusinessException("Campo materie vuoto a verificaNonNullitaCampi a UtenteDTO.");
		}

	}

}
