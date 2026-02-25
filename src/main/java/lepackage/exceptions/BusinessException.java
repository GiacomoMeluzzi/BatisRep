package lepackage.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 988687614953802930L;
	private Object errorObject;
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(String message, Object errorObject) {
		super(message);
		this.errorObject = errorObject;
	}
	
}
