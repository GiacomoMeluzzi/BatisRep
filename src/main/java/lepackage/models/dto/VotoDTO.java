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
public class VotoDTO extends SuperDTO {
	
	private Integer id;
	private String studenteId;
	private String professoreId;
	private Integer valore;
	private String studenteUsername;
	private String professoreUsername;
	private List<Integer> voti;

	@Override
	public void verificaNonNullitaCampi() throws BusinessException {
		System.out.println("Inizio verifica campi fondamentali VotoDTO.");
		if(studenteId == null || studenteId.equals("") || professoreId == null || professoreId.equals("") ) {
			throw new BusinessException("Campi fondamentali VotoDTO vuoti o nulli.");
		}
		System.out.println("Campi fondamentali VotoDTO verificati.");
	}

}
 