package lepackage.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lepackage.dao.UtenteDao;
import lepackage.dto.SuperDTO;
import lepackage.dto.UtenteDTO;
import lepackage.exceptions.BusinessException;
import lepackage.models.UtenteEntity;
import lepackage.utilities.UtilityClass;
import static lepackage.utilities.Constants.*;

@Service
public class UtenteService {

	private UtenteDao utenteDao;
	private MateriaEFacoltaService materiaEfacoltaService;

	public UtenteService(UtenteDao utenteDao, MateriaEFacoltaService materiaEfacoltaService) {
		this.utenteDao = utenteDao;
		this.materiaEfacoltaService = materiaEfacoltaService;
	}

	public SuperDTO findUtenteByUsername(UtenteDTO utenteDaCercare) throws Exception {
		try {
			System.out.println("Entra findUtenteyUsername.");
			if (utenteDaCercare == null) {
				throw new BusinessException("Utente da cercare è arrivato nullo al service a findUtetneByUsername.");
			}
			UtilityClass.regexCheckUnoFinoAQuattroCampi(ONE_REGEX_ARGUMENT, LOGIN_REGEX_USR,
					utenteDaCercare.getUsername(), null, null, null, null, null, null);
			UtenteEntity utenteTrovato = utenteDao.findUtenteByUsername(utenteDaCercare.getUsername());
			UtenteDTO utentePerFrontend = new UtenteDTO(utenteTrovato);
			SuperDTO oggettoPerFrontEnd = new SuperDTO("Utente trovato!", utentePerFrontend, HttpStatus.OK);
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			System.out.println("Lancio una BusinessException a UtenteService UtenteService findUtenteByUsername.");
			throw new BusinessException(e.getMessage() + " a UtenteService findUtenteByUsername.");
		} catch (Exception e) {
			System.out.println("Lancio una Exception a UtenteService UtenteService findUtenteByUsername.");
			throw e;
		}
	}

	public SuperDTO findUtenteByEmailEPassword(UtenteDTO utenteDaCercare) throws Exception {
		try {
			System.out.println("Entra findUtenteByEmailEPassword.");
			if (utenteDaCercare == null) {
				throw new BusinessException("Utente da cercare è arrivato nullo al service a findUtetneByUsernameJoinRUolo.");
			}
			UtilityClass.regexCheckUnoFinoAQuattroCampi(TWO_REGEX_ARGUMENTS, LOGIN_REGEX_MAIL,
					utenteDaCercare.getEmail(), LOGIN_REGEX_PSW, utenteDaCercare.getPassword(), null, null, null, null);
			UtenteEntity utenteTrovato = utenteDao.findUtenteByEmailEPasswordJoinRuolo(utenteDaCercare.getEmail(),
					utenteDaCercare.getPassword());
			UtenteDTO utentePerFrontend = new UtenteDTO(utenteTrovato);
			SuperDTO oggettoPerFrontEnd = new SuperDTO("Utente trovato!", utentePerFrontend, HttpStatus.OK);
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			if (e.getErrorObject() != null) {
				System.out.println("L'utente non è stato trovato.");
				throw e;
			}
			System.out.println("Lancio una BusinessException a UtenteService findUtenteByEmailEPasswordJoinRuolo.");
			throw new BusinessException(e.getMessage(), " a UtenteService findUtenteByEmailEPasswordJoinRuolo.");
		} catch (Exception e) {
			System.out.println("Lancio una Exception a UtenteService findUtenteByEmailEPasswordJoinRuolo.");
			throw e;
		}
	}
	
	public SuperDTO findUtenteConMaterieDaUsername (UtenteDTO utenteDaCercare) throws Exception {
		try {
			System.out.println("Entra findUtenteConMaterieDaUsername.");
			if (utenteDaCercare == null) {
				throw new BusinessException("Utente da cercare è arrivato nullo al service a findUtetneByUsernameJoinRuolo.");
			}
			UtilityClass.regexCheckUnoFinoAQuattroCampi(ONE_REGEX_ARGUMENT, LOGIN_REGEX_USR,
					utenteDaCercare.getUsername(), null, null, null, null, null, null);
			UtenteEntity utenteTrovato = utenteDao.findUtenteConMaterieDaUsername(utenteDaCercare.getUsername());
			if(utenteTrovato.getMaterie() == null || utenteTrovato.getMaterie().size() == 0) {
				throw new BusinessException(utenteTrovato.getUsername() + " non ha materie!");
			}
			UtenteDTO utentePerFrontend = new UtenteDTO(utenteTrovato);
			SuperDTO oggettoPerFrontEnd = new SuperDTO("Utente trovato!", utentePerFrontend, HttpStatus.OK);
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			System.out.println("Lancio una BusinessException a UtenteService findUtenteConMaterieDaUsername.");
			throw new BusinessException(e.getMessage() + " a UtenteService findUtenteConMaterieDaUsername." );
		}
		catch (Exception e) {
			System.out.println("Lancio una Exception a UtenteService findUtenteConMaterieDaUsername.");
			throw e;
		}
	}
	
	public SuperDTO tryRegistrazioneUtente (UtenteDTO utenteDaRegistrare) throws Exception {
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
