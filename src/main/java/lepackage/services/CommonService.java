package lepackage.services;

import static lepackage.utilities.Constants.*;

import lepackage.dao.MateriaEFacoltaDao;
import lepackage.dao.UtenteDao;
import lepackage.dto.ResponseDTO;
import lepackage.dto.UtenteDTO;
import lepackage.exceptions.BusinessException;
import lepackage.utilities.UtilityClass;

public class CommonService {

	private UtenteDao utenteDao;
	private MateriaEFacoltaDao materiaFacoltaDao;
	private UtenteService utenteService;
	private MateriaEFacoltaService materiaEFacoltaService;
	
	public CommonService(UtenteDao utenteDao, MateriaEFacoltaDao materiaEFacoltaDao,
			UtenteService utenteService, MateriaEFacoltaService materiaEFacoltaService) {
		this.utenteDao = utenteDao;
		this.materiaFacoltaDao = materiaEFacoltaDao;
		this.utenteService = utenteService;
		this.materiaEFacoltaService = materiaEFacoltaService;
	}
	
	public ResponseDTO tryRegistrazioneUtente (UtenteDTO utenteDaRegistrare) throws Exception {
		try {
			System.out.println("Entra tryRegistrazioneUtente");
			if(utenteDaRegistrare == null) {
				System.out.println("Lancio eccezione causa controllo utenteDTO nullo.");
				throw new BusinessException("UtenteDTO in arrivo tryRegistrazioneUtente nullo.");
			}
			System.out.println("Inizio controllo regex utenteDTO");
			UtilityClass.regexCheckUnoFinoAQuattroCampi(THREE_REGEX_ARGUMENTS,
					LOGIN_REGEX_MAIL, utenteDaRegistrare.getEmail(), 
					LOGIN_REGEX_USR, utenteDaRegistrare.getUsername(),
					LOGIN_REGEX_PSW,utenteDaRegistrare.getPassword(), 
					null, null);
			System.out.println("Controllo ruolo.");
			if (utenteDaRegistrare.getRuolo() == null) {
				System.out.println("Lancio eccezione causa ruolo utenteDTO nullo.");
				throw new BusinessException("Il ruolo è nullo.");
			}
			System.out.println("Ruolo accettato, controllo facoltà.");
			if (utenteDaRegistrare.getFacolta_id() == null) {
				System.out.println("Lancio eccezione causa facolta_id utenteDTO nullo.");
				throw new BusinessException("facolta_id è nullo");
			}
			System.out.println("Facolta_id ok, controllo materie.");
			if (utenteDaRegistrare.getMaterie() == null || utenteDaRegistrare.getMaterie().size() == 0) {
				System.out.println("Lancio eccezione causa materie utenteDTO nullo o vuoto.");
				throw new BusinessException("materie utenteDTO nullo o vuoto");
			}
			System.out.println("Tutti i controlli DTO superati, passo a chiamate DB.");
			materiaEFacoltaService.findFacoltaConMaterieById(utenteDaRegistrare.getFacolta_id());
			return null;
		} catch (BusinessException e) {
			System.out.println("Lancio una BusinessException a UtenteService tryRegistrazioneUtente.");
			throw new BusinessException(e.getMessage() + " a UtenteService tryRegistrazioneUtente.");
		} catch (Exception e) {
			System.out.println("Lancio una Exception a UtenteService tryRegistrazioneUtente.");
			throw e;
		}
	}
}
