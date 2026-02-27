package lepackage.models.pojos.utilities;

import static lepackage.models.pojos.utilities.Constants.LOGIN_REGEX_MAIL;
import static lepackage.models.pojos.utilities.Constants.LOGIN_REGEX_PSW;
import static lepackage.models.pojos.utilities.Constants.LOGIN_REGEX_USR;
import static lepackage.models.pojos.utilities.Constants.ONE_REGEX_ARGUMENT;
import static lepackage.models.pojos.utilities.Constants.THREE_REGEX_ARGUMENTS;
import static lepackage.models.pojos.utilities.Constants.TWO_REGEX_ARGUMENTS;

import org.springframework.stereotype.Component;

import lepackage.exceptions.BusinessException;
import lepackage.models.dto.SuperDTO;
import lepackage.models.dto.UtenteDTO;

@Component
public class Validatore {
	
	private UtilityClass<SuperDTO> utilityClass;
	
	public Validatore(UtilityClass<SuperDTO> utilityClass) {
		this.utilityClass = utilityClass;
	}
	
	public void verificaUtenteCercaUsername(UtenteDTO utenteDaControllare) throws Exception {
		System.out.println("Entra ValidationClass. Inizio controllo regex per ricerca tramite username.");
		utilityClass.verificaOggettoNonNull(utenteDaControllare);
		System.out.println("Entra findUtenteyUsername.");
		utilityClass.regexCheckUnoFinoAQuattroCampi(ONE_REGEX_ARGUMENT, LOGIN_REGEX_USR,
				utenteDaControllare.getUsername(), null, null, null, null, null, null);
	} 
	
	public void verificaUtentePerLogin(UtenteDTO utenteDaControllare) throws Exception {
		utilityClass.verificaOggettoNonNull(utenteDaControllare);
		System.out.println("Entra findUtenteByEmailEPassword.");
		utilityClass.regexCheckUnoFinoAQuattroCampi(TWO_REGEX_ARGUMENTS, LOGIN_REGEX_MAIL,
				utenteDaControllare.getEmail(), LOGIN_REGEX_PSW, utenteDaControllare.getPassword(), null, null, null, null);
	}

	public void verificaUtentePerRegistrazione (UtenteDTO utenteDaControllare) throws Exception {
		System.out.println("Entra ValidationClass. Inizio controllo regex per registrazione.");
		utenteDaControllare.verificaNonNullitaCampiPerRegister();
		utilityClass.regexCheckUnoFinoAQuattroCampi(THREE_REGEX_ARGUMENTS, LOGIN_REGEX_MAIL,
				utenteDaControllare.getEmail(), LOGIN_REGEX_USR, 
				utenteDaControllare.getUsername(), LOGIN_REGEX_PSW,
				utenteDaControllare.getPassword(), null, null);
		
		System.out.println("Controllo ruolo.");
		if (utenteDaControllare.getRuolo() == null) {
			System.out.println("Lancio eccezione causa ruolo utenteDTO nullo.");
			throw new BusinessException("Il ruolo è nullo.");
		}
		System.out.println("Ruolo accettato, controllo facoltà.");
		
		if (utenteDaControllare.getFacoltaId() == null) {
			System.out.println("Lancio eccezione causa facolta_id utenteDTO nullo.");
			throw new BusinessException("facolta_id è nullo");
		}
	}
	
}
