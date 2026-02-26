package lepackage.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lepackage.dto.ResponseDTO;
import lepackage.dto.UtenteDTO;
import lepackage.exceptions.BusinessException;
import lepackage.models.dao.UtenteDao;
import lepackage.models.entities.UtenteEntity;
import lepackage.models.pojos.utilities.ConvertitoreClass;
import lepackage.models.pojos.utilities.UtilityClass;

import static lepackage.models.pojos.utilities.Constants.*;

@Service
public class UtenteService {

	private UtenteDao utenteDao;
	private UtilityClass<UtenteDTO> utilityClass;

	public UtenteService(UtenteDao utenteDao, UtilityClass<UtenteDTO> utilityClass) {
		this.utenteDao = utenteDao;
		this.utilityClass = utilityClass;
	}

	public ResponseDTO findUtenteByUsername(UtenteDTO utenteDaCercare) throws Exception {
		try {
			utilityClass.verificaOggettoNonNull(utenteDaCercare);
			System.out.println("Entra findUtenteyUsername.");
			utilityClass.regexCheckUnoFinoAQuattroCampi(ONE_REGEX_ARGUMENT, LOGIN_REGEX_USR,
					utenteDaCercare.getUsername(), null, null, null, null, null, null);
			
			UtenteEntity utenteTrovato = utenteDao.findUtenteByUsername(utenteDaCercare.getUsername());
			UtenteDTO utentePerFrontend = ConvertitoreClass.utenteEntityToDtoNoFacoltaEMaterie(utenteTrovato);
			
			ResponseDTO oggettoPerFrontEnd = new ResponseDTO("Utente trovato!", utentePerFrontend, HttpStatus.OK);
			System.out.println("Ritorno oggetto a controller a findUtenteByUsername.");
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			System.out.println("Lancio una BusinessException a UtenteService UtenteService findUtenteByUsername.");
			throw new BusinessException(e.getMessage() + " a UtenteService findUtenteByUsername.");
		} catch (Exception e) {
			System.out.println("Lancio una Exception a UtenteService UtenteService findUtenteByUsername.");
			throw e;
		}
	}

	public ResponseDTO findUtenteByEmailEPassword(UtenteDTO utenteDaCercare) throws Exception {
		try {
			utilityClass.verificaOggettoNonNull(utenteDaCercare);
			System.out.println("Entra findUtenteByEmailEPassword.");
			utilityClass.regexCheckUnoFinoAQuattroCampi(TWO_REGEX_ARGUMENTS, LOGIN_REGEX_MAIL,
					utenteDaCercare.getEmail(), LOGIN_REGEX_PSW, utenteDaCercare.getPassword(), null, null, null, null);
			UtenteEntity utenteTrovato = utenteDao.findUtenteByEmailEPasswordJoinRuolo(utenteDaCercare.getEmail(),
					utenteDaCercare.getPassword());
			UtenteDTO utentePerFrontend = ConvertitoreClass.utenteEntityToDtoNoFacoltaEMaterie(utenteTrovato);
			
			ResponseDTO oggettoPerFrontEnd = new ResponseDTO("Utente trovato!", utentePerFrontend, HttpStatus.OK);
			System.out.println("Ritorno oggetto a controller a findUtenteByEmailEPassword.");
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

	public ResponseDTO findUtenteConMaterieDaUsername(UtenteDTO utenteDaCercare) throws Exception {
		try {
			utilityClass.verificaOggettoNonNull(utenteDaCercare);
			System.out.println("Entra findUtenteConMaterieDaUsername.");
			utilityClass.regexCheckUnoFinoAQuattroCampi(ONE_REGEX_ARGUMENT, LOGIN_REGEX_USR,
					utenteDaCercare.getUsername(), null, null, null, null, null, null);
			UtenteEntity utenteTrovato = utenteDao.findUtenteConMaterieDaUsername(utenteDaCercare.getUsername());
			if (utenteTrovato.getMaterie() == null || utenteTrovato.getMaterie().size() == 0) {
				throw new BusinessException(utenteTrovato.getUsername() + " non ha materie!");
			}
			UtenteDTO utentePerFrontend = ConvertitoreClass.utenteEntityToDTO(utenteTrovato);
			
			System.out.println("Ritorno oggetto a controller a findUtenteConMaterieDaUsername.");
			ResponseDTO oggettoPerFrontEnd = new ResponseDTO("Utente trovato!", utentePerFrontend, HttpStatus.OK);
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			System.out.println("Lancio una BusinessException a UtenteService findUtenteConMaterieDaUsername.");
			throw new BusinessException(e.getMessage() + " a UtenteService findUtenteConMaterieDaUsername.");
		} catch (Exception e) {
			System.out.println("Lancio una Exception a UtenteService findUtenteConMaterieDaUsername.");
			throw e;
		}
	}

}
