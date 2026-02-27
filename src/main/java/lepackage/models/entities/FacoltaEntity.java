package lepackage.models.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FacoltaEntity {

	private Integer id;
	private String nome;
	private List<UtenteEntity> utenti;
	private List<MateriaEntity> materie;
	private List<Integer> utentiId;
	private List<Integer> materieId;
	
	public FacoltaEntity(Integer id, List<Integer> utentiId) {
		super();
		this.id = id;
		this.utentiId = utentiId;
	}
	
	

}
