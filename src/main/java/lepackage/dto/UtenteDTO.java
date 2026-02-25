package lepackage.dto;

import java.util.ArrayList;
import java.util.List;

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
public class UtenteDTO {

	private Integer id;	
	private String username;
	private String email;
	private String password;
	private Ruolo ruolo;
	private List<String> materie;
	private Integer facolta_id;
	
	public UtenteDTO (UtenteEntity utenteDaConvertireSenzaPassword) {
		
		this.id = utenteDaConvertireSenzaPassword.getId();
		this.username = utenteDaConvertireSenzaPassword.getUsername();
		this.email = utenteDaConvertireSenzaPassword.getEmail();
		this.password = null;
		this.facolta_id = utenteDaConvertireSenzaPassword.getFacolta_id();
		if (null != utenteDaConvertireSenzaPassword.getRuolo()) {
		this.ruolo = utenteDaConvertireSenzaPassword.getRuolo().getNome();
		}
		if(utenteDaConvertireSenzaPassword.getMaterie() != null &&
				utenteDaConvertireSenzaPassword.getMaterie().size() != 0) {
			List<String> nomiMaterie = new ArrayList<String>();
			for (MateriaEntity materieDaConvertire : utenteDaConvertireSenzaPassword.getMaterie()) {
				nomiMaterie.add(materieDaConvertire.getNome());
			}
			this.materie = nomiMaterie;
		}
		System.out.println("Convertita entità in DTO.");
	}
	
}
