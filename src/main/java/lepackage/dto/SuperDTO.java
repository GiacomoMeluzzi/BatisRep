package lepackage.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuperDTO implements Serializable {

	private static final long serialVersionUID = 8281710753265902167L;
	private String message;
	private Object dtoForFrontEnd;
	private HttpStatus statusCode;

}
