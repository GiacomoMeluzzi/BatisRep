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
public class UtenteMateriaDTO extends SuperDTO {
	//sempre avere id
	private Integer utenteId;
	private List<Integer> materiaId;
	
	@Override
	public void verificaNonNullitaCampi() throws BusinessException {
		if(utenteId == null || materiaId == null || materiaId.size() == 0) {
			throw new BusinessException("Errore, campo o campi vutodi a UtenteMateriaDTO verficiaNullatCampi.");
		}
		System.out.println("Tutti i campi di UtenteMateriaDTO verificati non nuli o vuoti.");
	}
	
}
