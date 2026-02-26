package lepackage.models.dto;

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

	@Override
	public void verificaNonNullitaCampi() throws BusinessException {
	}

}
