package lepackage.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class FacoltaDTO implements Serializable {

	private static final long serialVersionUID = 3051203338431526641L;
	private Integer id;
	private String nome;
	private List<String> materie;
	
	public FacoltaDTO(FacoltaEntity facoltaDaConvertire) {
		this.id = facoltaDaConvertire.getId();
		this.nome = facoltaDaConvertire.getNome();
		if (facoltaDaConvertire.getMaterie() != null && 
				facoltaDaConvertire.getMaterie().size() != 0) {
			List<String> materiePerDto = new ArrayList<String>();
			for (MateriaEntity materiaDaConvertire : facoltaDaConvertire.getMaterie()) {
				materiePerDto.add(materiaDaConvertire.getNome());
			}
			this.materie = materiePerDto;
		}
		System.out.println("Oggetto facoltaEntity convertito in DTO.");
	}
	
}
