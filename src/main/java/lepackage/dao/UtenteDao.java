package lepackage.dao;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lepackage.dto.FacoltaUtenteDTO;
import lepackage.dto.UtenteDTO;
import lepackage.dto.UtenteMateriaDTO;
import lepackage.exceptions.BusinessException;
import lepackage.mappers.MateriaEFacoltaMapper;
import lepackage.mappers.UtenteMapper;
import lepackage.models.UtenteEntity;
import lepackage.utils.SqlMapFactory;

@Component
public class UtenteDao implements UtenteMapper {

	private UtenteMapper utenteMapper = null;
	private MateriaEFacoltaDao materiaEfacoltaDao;

	@Override
	public UtenteEntity findUtenteByUsername(String usernameInEntrata) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			utenteMapper = (UtenteMapper) SqlMapFactory.instance().getMapper(UtenteMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findUtenteByUsername.");
			UtenteEntity utenteTrovato = utenteMapper.findUtenteByUsername(usernameInEntrata);
			if (null == utenteTrovato) {
				System.out.println("Utente non trovato");
				throw new BusinessException("Utente non trovato.");
			}
			SqlMapFactory.instance().commitSession();
			System.out.println("Utente trovato nel DB.");
			return utenteTrovato;
		} catch (BusinessException e) {
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} catch (Exception e) {
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	@Override
	public UtenteEntity findUtenteByEmailEPassword(String emailInEntrata, String passwordInEntrata) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			utenteMapper = (UtenteMapper) SqlMapFactory.instance().getMapper(UtenteMapper.class);
			System.out
					.println("Aperta istanza SqlMapFactory, inizio query a 				findUtenteByEmailEPassword.");
			UtenteEntity utenteTrovato = utenteMapper.findUtenteByEmailEPassword(emailInEntrata, passwordInEntrata);
			if (null == utenteTrovato) {
				System.out.println("Utente non trovato");
				throw new BusinessException("Utente non trovato.", HttpStatus.FORBIDDEN);
			}
			SqlMapFactory.instance().commitSession();
			System.out.println("Utente trovato nel DB.");
			return utenteTrovato;
		} catch (BusinessException e) {
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} catch (Exception e) {
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	@Override
	public UtenteEntity findUtenteByEmailEPasswordJoinRuolo(String emailInEntrata, String passwordInEntrata)
			throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			utenteMapper = (UtenteMapper) SqlMapFactory.instance().getMapper(UtenteMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findUtenteByEmailEPassword.");
			UtenteEntity utenteTrovato = utenteMapper.findUtenteByEmailEPasswordJoinRuolo(emailInEntrata,
					passwordInEntrata);
			if (null == utenteTrovato) {
				System.out.println("Utente non trovato");
				throw new BusinessException("Utente non trovato.", HttpStatus.FORBIDDEN);
			}
			SqlMapFactory.instance().commitSession();
			System.out.println("Utente trovato nel DB.");
			return utenteTrovato;
		} catch (BusinessException e) {
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} catch (Exception e) {
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	@Override
	public UtenteEntity findUtenteConMaterieDaUsername(String usernameInEntrata) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			utenteMapper = (UtenteMapper) SqlMapFactory.instance().getMapper(UtenteMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findUtenteConMaterieDaUsername.");
			UtenteEntity utenteTrovato = utenteMapper.findUtenteConMaterieDaUsername(usernameInEntrata);
			if (null == utenteTrovato) {
				System.out.println("Utente non trovato");
				throw new BusinessException("Utente non trovato.", HttpStatus.FORBIDDEN);
			}
			SqlMapFactory.instance().commitSession();
			System.out.println("Utente trovato nel DB.");
			return utenteTrovato;
		} catch (BusinessException e) {
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	public Integer registraNuovoUtente(UtenteDTO nuovoUtente, UtenteMateriaDTO nuovoUtenteMateriaDTO,
			FacoltaUtenteDTO nuovaFacoltaUtenteDTO) throws Exception {
		try {
			System.out.println("Entro inserisciNuovoUtente in UtenteDao.");
			SqlMapFactory.instance().openSession();
			nuovoUtente.verificaNonNullitaCampi();
			materiaEfacoltaDao = new MateriaEFacoltaDao();
			System.out.println("Preparazione a inserimento nuovo utente ultimate.");
			
			utenteMapper = (UtenteMapper) SqlMapFactory.instance().getMapper(UtenteMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query necessarie a registerNuovoUtente.");
			
			materiaEfacoltaDao.insertMateriaEUtente(nuovoUtenteMateriaDTO);
			System.out.println("Utente con materie inserite in bridge table.");
			
			materiaEfacoltaDao.insertFacoltaEUtente(nuovaFacoltaUtenteDTO);
			System.out.println("Facoltà con utente inserita in bridge table, insert necessari completati.");
			
			Integer recordInseritoUtente = utenteMapper.inserisciNuovoUtente(nuovoUtente);
			System.out.println("Utente inserito! " + recordInseritoUtente + " cambiamento apportato!");
			return recordInseritoUtente;
		} catch (Exception e) {
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	@Override
	public Integer inserisciNuovoUtente(UtenteDTO nuovoUtente) throws Exception {
		try {
		utenteMapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
		nuovoUtente.verificaNonNullitaCampi();
		Integer recordInseritoUtente = utenteMapper.inserisciNuovoUtente(nuovoUtente);
		if(recordInseritoUtente == null || recordInseritoUtente != 1) {
			System.out.println("Errore nell'inserimento utente a inserisciNuovoUtente.");
			throw new BusinessException("Errore nell'inserimento del nuovo utente.");
		}
		return recordInseritoUtente;
		} catch (BusinessException e) {
			System.out.println("BusinessException a inserisciNuovoUtente.");
			throw e;
		} catch (Exception e) {
			System.out.println("Eccezione a inserisciNuovoUtente " + e.getMessage());
			throw e;
		}
	}

}
