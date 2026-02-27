package lepackage.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lepackage.models.dto.FacoltaUtenteDTO;
import lepackage.models.dto.UtenteDTO;
import lepackage.models.dto.UtenteMateriaDTO;
import lepackage.exceptions.BusinessException;
import lepackage.mappers.UtenteMapper;
import lepackage.models.entities.UtenteEntity;
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
				throw new BusinessException("Utente non trovato.", HttpStatus.NO_CONTENT);
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
				throw new BusinessException("Utente non trovato.", HttpStatus.NO_CONTENT);
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
	public UtenteEntity findUtenteConMaterieDaUsername(String usernameInEntrata) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			utenteMapper = (UtenteMapper) SqlMapFactory.instance().getMapper(UtenteMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findUtenteConMaterieDaUsername.");
			UtenteEntity utenteTrovato = utenteMapper.findUtenteConMaterieDaUsername(usernameInEntrata);
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
			System.out.println(e.getMessage());
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	@Override
	public Integer inserisciNuovoUtente(UtenteDTO nuovoUtente) throws Exception {
		try {
			System.out.println("Entro in inserisciNuovoUtente.");
			utenteMapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			nuovoUtente.verificaNonNullitaCampi();
			Integer recordInseritoUtente = utenteMapper.inserisciNuovoUtente(nuovoUtente);
			if (recordInseritoUtente == null || recordInseritoUtente != 1) {
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

	public Integer registraNuovoUtente(UtenteDTO nuovoUtente) throws Exception {
		try {
			System.out.println("Entro registraNuovoUtente in UtenteDao.");
			SqlMapFactory.instance().openSession();
			materiaEfacoltaDao = new MateriaEFacoltaDao();
			System.out.println("Preparazione a inserimento nuovo utente ultimate.");

			utenteMapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			Integer recordInseritoUtente = utenteMapper.inserisciNuovoUtente(nuovoUtente);
			System.out.println("Utente inserito! " + recordInseritoUtente + " cambiamento apportato!");

			System.out.println("Creo DTO per bridge tables.");
			UtenteMateriaDTO nuovoUtenteMateriaDTO = new UtenteMateriaDTO(nuovoUtente.getId(),
					nuovoUtente.getMaterieId());
			List<Integer> idUtentiFacolta = new ArrayList<Integer>();
			idUtentiFacolta.add(nuovoUtente.getId());
			FacoltaUtenteDTO nuovaFacoltaUtenteDTO = new FacoltaUtenteDTO(nuovoUtente.getFacoltaId(), idUtentiFacolta);
			System.out.println("Creazione DTO ultimata.");

			materiaEfacoltaDao.insertMateriaEUtente(nuovoUtenteMateriaDTO);
			System.out.println("Utente con materie inserite in bridge table.");

			materiaEfacoltaDao.insertFacoltaEUtente(nuovaFacoltaUtenteDTO);
			System.out.println("Facoltà con utente inserita in bridge table, insert necessari completati.");

			SqlMapFactory.instance().commitSession();
			return recordInseritoUtente;
		} catch (PersistenceException e) {
			System.out.println("L'utente è già esistente.");
			SqlMapFactory.instance().rollbackSession();
			throw new BusinessException("L'utente esiste già.");
		} catch (BusinessException e) {
			System.out.println("Eccezione a registraNuovoUtente.");
			SqlMapFactory.instance().rollbackSession();
			throw new BusinessException("registraNuovoUtente " + e.getMessage());
		} catch (Exception e) {
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

}
