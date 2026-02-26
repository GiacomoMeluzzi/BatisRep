package lepackage.models.pojos.utilities;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lepackage.exceptions.BusinessException;
import lepackage.varie.Ruolo;

@Component
public class UtilityClass<T> {

	public boolean regexCheckUnoFinoAQuattroCampi(int numberOfRegexArguments, String firstRegexArgs,
			String firstInputArgs, String secondRegexArgs, String secondInputArgs, String thirdRegexArgs,
			String thirdInputArgs, String fourthRegexArgs, String fourthInputArgs) throws BusinessException {
		System.out.println("Chiamata utiliy class per controllo regex.");
		try {
			boolean correctnessChecker = true;
			switch (numberOfRegexArguments) {
			case 4:
				if (fourthRegexArgs == null || fourthInputArgs == null) {
					throw new BusinessException("Errore check regex al quarto argomento, nullo.");
				} else {
					System.out.println("Input regexCheck " + fourthInputArgs);
					if (Pattern.matches(fourthRegexArgs, fourthInputArgs)) {
						System.out.println("Regex check " + fourthInputArgs + ", successo!");
					} else {
						System.out.println("Regex check " + fourthInputArgs + ", errore!");
						throw new BusinessException("Errore check regex a quarto argomento regex.");
					}
				}
			case 3:
				if (thirdRegexArgs == null || thirdInputArgs == null) {
					throw new BusinessException("Errore check regex al terzo argomento, nullo.");
				} else {
					System.out.println("Input regexCheck " + thirdInputArgs);
					if (Pattern.matches(thirdRegexArgs, thirdInputArgs)) {
						System.out.println("Regex check " + thirdInputArgs + ", successo!");
					} else {
						System.out.println("Regex check " + thirdInputArgs + ", errore!");
						throw new BusinessException("Errore check regex a terzo argomento regex.");
					}
				}
			case 2:
				if (secondRegexArgs == null || secondInputArgs == null) {
					throw new BusinessException("Errore check regex al secondo argomento, nullo.");
				} else {
					System.out.println("Input regexCheck " + secondInputArgs);
					if (Pattern.matches(secondRegexArgs, secondInputArgs)) {
						System.out.println("Regex check " + secondInputArgs + ", successo!");
					} else {
						System.out.println("Regex check " + secondInputArgs + ", errore!");
						throw new BusinessException("Errore check regex a secondo argomento regex.");
					}
				}
			case 1:
				if (firstRegexArgs == null || firstInputArgs == null) {
					throw new BusinessException("Errore check regex al primo argomento, nullo.");
				} else {
					System.out.println("Input regexCheck " + firstInputArgs);
					if (Pattern.matches(firstRegexArgs, firstInputArgs)) {
						System.out.println("Regex check " + firstInputArgs + ", successo!");
					} else {
						System.out.println("Regex check " + firstInputArgs + ", errore!");
						throw new BusinessException("Errore check regex a primo argomento regex.");
					}
				}
				System.out.println("Tutte le regex sono state validate correttamente.");
				break;
			}
			return correctnessChecker;
		} catch (BusinessException e) {
			throw e;
		}
	}

	public boolean roleCheck(Ruolo ruoloDaControllare, Ruolo ruoloACuiParagonare) throws BusinessException {
		System.out.println("Chiamata utilityClass per roleCheck.");
		if (ruoloDaControllare == null) {
			System.out.println("Role check, errore, ruolo nullo!");
			throw new BusinessException("Ruolo nullo in roleCheck.");
		}
		System.out.println("Input rolechek: " + ruoloDaControllare);
		if (ruoloACuiParagonare == ruoloDaControllare) {
			System.out.println("Role check, successo!");
			return true;
		} else {
			System.out.println("Role check, errore!");
			throw new BusinessException("Ruolo invalido a roleCheck!");
		}
	}

	public void verificaOggettoNonNull(T oggettoDaControllare) throws BusinessException
	{
		System.out.println("Verifica oggetto non null o vuoto.");
		if (oggettoDaControllare == null) {
			System.out.println("Oggetto nullo a verificaOggettoNonNull.");
			throw new BusinessException("Oggetto è null.");
		}
		if (oggettoDaControllare instanceof String && oggettoDaControllare.equals("")){
			System.out.println("Stringa vuota a verificaOggettoNonNull.");
			throw new BusinessException("Stringa vuota.");
		}
		if (oggettoDaControllare instanceof Object[]) {
			Object[] arrayDaControllare = (Object[]) oggettoDaControllare;
			if(arrayDaControllare.length == 0) {
			System.out.println("Array vuoto a verificaOggettoNonNull.");
			throw new BusinessException("Array vuoto.");
			}
		}
		if (oggettoDaControllare instanceof java.util.List<?>) {
			java.util.List<?> listaDaControllare = (java.util.List<?>) oggettoDaControllare;
			if(listaDaControllare.size() == 0) {
			System.out.println("Lista vuota a verificaOggettoNonNull.");
			throw new BusinessException("Lista vuota.");
			}
		}
		System.out.println("Verifica non nullità completata.");
	}

}
