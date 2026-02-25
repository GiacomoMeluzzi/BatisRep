package lepackage.utilities;

import java.util.regex.Pattern;

import lepackage.exceptions.BusinessException;
import lepackage.varie.Ruolo;

public class UtilityClass {

	public static boolean regexCheckUnoFinoAQuattroCampi(int numberOfRegexArguments, String firstRegexArgs,
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

	public static boolean roleCheck(Ruolo ruoloDaControllare, Ruolo ruoloACuiParagonare) throws BusinessException {
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

}
