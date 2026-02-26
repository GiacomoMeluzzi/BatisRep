package lepackage.models.dto;

import java.util.List;

import lepackage.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacoltaUtenteDTO extends SuperDTO {

	Integer facoltaId;
	List<Integer> utenteId;
	
	@Override
	public void verificaNonNullitaCampi() throws BusinessException {
		if(facoltaId == null || utenteId == null || utenteId.size() == 0) {
			throw new BusinessException("Errore, campo o campi vutodi a UtenteMateriaDTO verficiaNullatCampi.");
		}
		System.out.println("Tutti i campi di FacoltaUtenteDTO verificati non nuli o vuoti.");
	}

}
