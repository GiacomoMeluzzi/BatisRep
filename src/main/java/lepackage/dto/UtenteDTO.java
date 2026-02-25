package lepackage.dto;

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
	
	public UtenteDTO (UtenteEntity utenteDaConvertireSenzaPassword) {
		
		this.id = utenteDaConvertireSenzaPassword.getId();
		this.username = utenteDaConvertireSenzaPassword.getUsername();
		this.email = utenteDaConvertireSenzaPassword.getEmail();
		this.password = null;
		if (null != utenteDaConvertireSenzaPassword.getRuolo()) {
		this.ruolo = utenteDaConvertireSenzaPassword.getRuolo().getNome();
		}
		System.out.println("Convertita entità in DTO.");
	}
	
}
