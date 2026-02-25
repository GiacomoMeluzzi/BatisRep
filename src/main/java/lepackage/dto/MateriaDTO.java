package lepackage.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDTO implements Serializable {

	private static final long serialVersionUID = 3051203338431526641L;
	private Integer id;
	private String nome;
	
}