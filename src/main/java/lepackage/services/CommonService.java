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
import lepackage.exceptions.BusinessException;
import lepackage.dao.MateriaDao;
import lepackage.dao.interfaces.FacoltaDaoInterface;
import lepackage.dao.interfaces.MateriaDaoInterface;
import lepackage.dao.interfaces.UtenteDaoInterface;
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
			
			if (utenteDao.inserisciNuovoUtente(utenteDaRegistrare) == 0) {
				System.out.println("Errore nell'inserimento utente a inserisciNuovoUtente.");
				throw new BusinessException("Errore nell'inserimento del nuovo utente.");
			}
			System.out.println("Utente inserito.");
			
			System.out.println("Creo DTO per bridge tables.");
			UtenteMateriaDTO nuovoUtenteMateriaDTO = new UtenteMateriaDTO(utenteDaRegistrare.getId(),
					utenteDaRegistrare.getMaterieId());
			List<Integer> idUtentiFacolta = new ArrayList<Integer>();
			idUtentiFacolta.add(utenteDaRegistrare.getId());
			FacoltaUtenteDTO nuovaFacoltaUtenteDTO = new FacoltaUtenteDTO(utenteDaRegistrare.getFacoltaId(), idUtentiFacolta);	
			System.out.println("Creazione DTO ultimata.");
			
			Integer materiaUtenteResult = materiaDao.insertMateriaEUtente(nuovoUtenteMateriaDTO);
			if (materiaUtenteResult == null || materiaUtenteResult != 1) {
				System.out.println("Errore nell'inserimento materiaUtente a inserisciNuovoUtente.");
				throw new BusinessException("Errore nell'inserimento del nuovo utente.");
			}
			System.out.println("Utente con materie inserite in bridge table.");

			Integer materiaFacoltaResult = facoltaDao.insertFacoltaEUtente(nuovaFacoltaUtenteDTO);
			if (materiaFacoltaResult == null || materiaFacoltaResult != 1) {
				System.out.println("Errore nell'inserimento materiaUtente a inserisciNuovoUtente.");
				throw new BusinessException("Errore nell'inserimento del nuovo utente.");
			}
			System.out.println("Facoltà con utente inserita in bridge table, insert necessari completati.");
			
			UtenteDTO nuovoUtentePerFrontEnd = new UtenteDTO(utenteDaRegistrare.getUsername(), utenteDaRegistrare.getEmail(), utenteDaRegistrare.getRuolo());			
			SqlMapFactory.instance().commitSession();
			return new ResponseDTO("Utente registrato!", nuovoUtentePerFrontEnd, HttpStatus.OK);
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
