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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = 8281710753265902167L;
	private String message;
	private Object dtoForFrontEnd;
	private HttpStatus statusCode;

	public ResponseDTO(Object dtoForFrontEnd) {
		this.dtoForFrontEnd = dtoForFrontEnd;
	}
	
}
