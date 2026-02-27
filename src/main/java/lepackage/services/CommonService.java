package lepackage.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lepackage.models.dto.FacoltaUtenteDTO;
import lepackage.models.dto.ResponseDTO;
import lepackage.models.dto.UtenteDTO;
import lepackage.models.dto.UtenteMateriaDTO;
import lepackage.models.entities.FacoltaEntity;
import lepackage.models.entities.MateriaEntity;
import lepackage.models.entities.UtenteEntity;
import lepackage.exceptions.BusinessException;
import lepackage.dao.MateriaDao;
import lepackage.dao.interfaces.FacoltaDaoInterface;
import lepackage.dao.interfaces.MateriaDaoInterface;
import lepackage.dao.interfaces.UtenteDaoInterface;
import lepackage.models.pojos.utilities.Convertitore;
import lepackage.models.pojos.utilities.Validatore;
import lepackage.services.interfaces.CommonServiceInterface;
import lepackage.utils.SqlMapFactory;

@Component
public class CommonService implements CommonServiceInterface {

	private UtenteDaoInterface utenteDao;
	private MateriaDaoInterface materiaDao;
	private FacoltaDaoInterface facoltaDao;
	private Validatore validatore;

	public CommonService(UtenteDaoInterface utenteDao, MateriaDaoInterface materiaDao, FacoltaDaoInterface facoltaDao,
			Validatore validatore) {
		this.utenteDao = utenteDao;
		this.facoltaDao = facoltaDao;
		this.materiaDao = materiaDao;
		this.validatore = validatore;
	}

	public ResponseDTO tryRegistrazioneUtente(UtenteDTO utenteDaRegistrare) throws Exception {
		try {
			validatore.verificaUtentePerRegistrazione(utenteDaRegistrare);
			System.out.println("Tutti i controlli DTO superati, passo a chiamate DB.");
			
			SqlMapFactory.instance().openSession(); 
			
			UtenteEntity utenteDaInserire = Convertitore.utenteDTOtoEntityPerRegistrazione(utenteDaRegistrare);
			if (utenteDao.inserisciNuovoUtente(utenteDaInserire) == 0) {
				System.out.println("Errore nell'inserimento utente a inserisciNuovoUtente.");
				throw new BusinessException("Errore nell'inserimento del nuovo utente.");
			}
			System.out.println("Utente inserito.");
			
			System.out.println("Creo oggetti per bridge tables.");
			List<Integer> idUtenteFacolta = new ArrayList<Integer>();
			idUtenteFacolta.add(utenteDaInserire.getId());
			FacoltaEntity nuovaFacoltaConUtente = new FacoltaEntity(utenteDaRegistrare.getFacoltaId(), idUtenteFacolta);
			
			int materiaUtenteResult = materiaDao.insertMateriaEUtente(utenteDaInserire);
			if (materiaUtenteResult == 0) {
				System.out.println("Errore nell'inserimento materiaUtente a inserisciNuovoUtente.");
				throw new BusinessException("Errore nell'inserimento del nuovo utente.");
			}
			System.out.println("Utente con materie inseritee.");

			int materiaFacoltaResult = facoltaDao.insertFacoltaEUtente(nuovaFacoltaConUtente);
			if (materiaFacoltaResult == 0) {
				System.out.println("Errore nell'inserimento materiaUtente a inserisciNuovoUtente.");
				throw new BusinessException("Errore nell'inserimento del nuovo utente.");
			}
			System.out.println("Facoltà con utente, insert necessari completati.");
			
			UtenteDTO nuovoUtentePerFrontEnd = new UtenteDTO(utenteDaRegistrare.getUsername(), utenteDaRegistrare.getEmail(), utenteDaRegistrare.getRuolo());			
			SqlMapFactory.instance().commitSession();
			return new ResponseDTO(nuovoUtentePerFrontEnd);
		} catch (PersistenceException e) {
			System.out.println("L'utente esiste già.");
			SqlMapFactory.instance().rollbackSession();
			throw new BusinessException("L'utente esiste già.");
		} catch (BusinessException e) {
			System.out.println("Lancio una BusinessException a UtenteService tryRegistrazioneUtente.");
			SqlMapFactory.instance().rollbackSession();
			throw new BusinessException(e.getMessage() + " a UtenteService tryRegistrazioneUtente.");
		} catch (Exception e) {
			System.out.println("Lancio una Exception a UtenteService tryRegistrazioneUtente " + e.getMessage());
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}
}
