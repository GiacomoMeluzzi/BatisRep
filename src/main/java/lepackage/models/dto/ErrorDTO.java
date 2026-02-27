package lepackage.models.dto;

import java.io.Serializable;
import org.springframework.http.HttpStatus;

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
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = -6759039625599793340L;
	private HttpStatus errorStatus;
	
}
