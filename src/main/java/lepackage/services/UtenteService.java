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

	public UtenteService(UtenteDao utenteDao) {
		this.utenteDao = utenteDao;
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
			throw new BusinessException(e.getMessage() + " a UtenteService findUtenteByUsername.s");
		} catch (Exception e) {
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
				throw e;
			}
			throw new BusinessException(e.getMessage(), " a UtenteService findUtenteByEmailEPasswordJoinRuolo.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	public SuperDTO findUtenteConMaterieDaUsername (UtenteDTO utenteDaCercare) throws Exception {
		try {
			System.out.println("Entra findUtenteConMaterieDaUsername.");
			if (utenteDaCercare == null) {
				throw new BusinessException("Utente da cercare è arrivato nullo al service a findUtetneByUsernameJoinRUolo.");
			}
			UtilityClass.regexCheckUnoFinoAQuattroCampi(ONE_REGEX_ARGUMENT, LOGIN_REGEX_USR,
					utenteDaCercare.getUsername(), null, null, null, null, null, null);
			UtenteEntity utenteTrovato = utenteDao.findUtenteConMaterieDaUsername(utenteDaCercare.getUsername());
			UtenteDTO utentePerFrontend = new UtenteDTO(utenteTrovato);
			SuperDTO oggettoPerFrontEnd = new SuperDTO("Utente trovato!", utentePerFrontend, HttpStatus.OK);
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage() + " a UtenteService findUtenteConMaterieDaUsername" );
		}
		catch (Exception e) {
			throw e;
		}
	}

}
