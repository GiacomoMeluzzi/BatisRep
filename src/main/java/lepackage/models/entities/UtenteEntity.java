package lepackage.models.entities;

import java.util.List;
import java.util.Objects;

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
public class UtenteEntity {
	
	private Integer id;	
	private String username;
	private String email;
	private String password;
	private RuoloEntity ruolo;
	private Integer facolta_id;
	private List<MateriaEntity> materie;
	private List<Integer> materieId;
	
	@Override
	public int hashCode() {
		return Objects.hash(username);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UtenteEntity other = (UtenteEntity) obj;
		return Objects.equals(username, other.username);
	}
	
}
