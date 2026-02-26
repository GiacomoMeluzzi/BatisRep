package lepackage.models;

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
public class VotoEntity {
	
	private Integer id;
	private Integer studente_id;
	private Integer professore_id;
	private Integer materia_id;
	private Integer valore;
	
}
