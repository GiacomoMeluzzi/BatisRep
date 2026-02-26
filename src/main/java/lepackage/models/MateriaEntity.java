package lepackage.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MateriaEntity {

	private Integer id;
	private String nome;
	private List<UtenteEntity> utenti;
	
}
