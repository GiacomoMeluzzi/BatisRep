package lepackage.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lepackage.models.dto.ResponseDTO;
import lepackage.models.dto.UtenteDTO;
import lepackage.exceptions.BusinessException;
import lepackage.dao.UtenteDao;
import lepackage.models.entities.UtenteEntity;
import lepackage.models.pojos.utilities.Convertitore;
import lepackage.models.pojos.utilities.UtilityClass;
import lepackage.models.pojos.utilities.Validatore;
import lepackage.services.interfaces.UtenteServiceInterface;
import lepackage.utils.SqlMapFactory;

import static lepackage.models.pojos.utilities.Constants.*;

@Service
public class UtenteService implements UtenteServiceInterface {

	private UtenteDao utenteDao;
	private UtilityClass<UtenteDTO> utilityClass;
	private Validatore validationClass;

	public UtenteService(UtenteDao utenteDao, UtilityClass<UtenteDTO> utilityClass, Validatore validationClass) {
		this.utenteDao = utenteDao;
		this.utilityClass = utilityClass;
		this.validationClass = validationClass;
	}

	public ResponseDTO findUtenteByUsername(UtenteDTO utenteDaCercare) throws Exception {
		try {
			System.out.println("Entro findUtenteByUsername a UtenteService.");
			validationClass.verificaUtenteCercaUsername(utenteDaCercare);
			
			SqlMapFactory.instance().openSession();
			
			UtenteEntity utenteTrovato = utenteDao.findUtenteByUsername(utenteDaCercare.getUsername());
			UtenteDTO utentePerFrontend = Convertitore.utenteEntityToDtoNoFacoltaEMaterie(utenteTrovato);
			
			SqlMapFactory.instance().commitSession();
			
			ResponseDTO oggettoPerFrontEnd = new ResponseDTO("Utente trovato!", utentePerFrontend, HttpStatus.OK);
			System.out.println("Ritorno oggetto a controller a findUtenteByUsername.");
			
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			if (e.getErrorObject() != null) {
				System.out.println("L'utente non è stato trovato.");
				throw e;
			}
			System.out.println("Lancio una BusinessException a UtenteService UtenteService findUtenteByUsername.");
			SqlMapFactory.instance().rollbackSession();
			throw new BusinessException(e.getMessage() + " a UtenteService findUtenteByUsername.");
		} catch (Exception e) {
			System.out.println("Lancio una Exception a UtenteService UtenteService findUtenteByUsername.");
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	public ResponseDTO findUtenteByEmailEPassword(UtenteDTO utenteDaCercare) throws Exception {
		try {
			
			UtenteEntity utenteTrovato = utenteDao.findUtenteByEmailEPasswordJoinRuolo(utenteDaCercare.getEmail(),
					utenteDaCercare.getPassword());
			UtenteDTO utentePerFrontend = Convertitore.utenteEntityToDtoNoFacoltaEMaterie(utenteTrovato);
			
			ResponseDTO oggettoPerFrontEnd = new ResponseDTO("Utente trovato!", utentePerFrontend, HttpStatus.OK);
			System.out.println("Ritorno oggetto a controller a findUtenteByEmailEPassword.");
			return oggettoPerFrontEnd;
		} catch (BusinessException e) {
			if (e.getErrorObject() != null) {
				System.out.println("L'utente non è stato trovato.");
				throw e;
			}
			System.out.println("Lancio una BusinessException a UtenteService findUtenteByEmailEPasswordJoinRuolo.");
			throw new BusinessException(e.getMessage() + " a UtenteService findUtenteByEmailEPasswordJoinRuolo.");
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
			UtenteDTO utentePerFrontend = Convertitore.utenteEntityToDTO(utenteTrovato);
			
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
