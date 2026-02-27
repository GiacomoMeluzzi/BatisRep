package lepackage.services;

import static lepackage.models.pojos.utilities.Constants.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lepackage.models.dto.ErrorDTO;
import lepackage.models.dto.ResponseDTO;
import lepackage.models.dto.SuperDTO;
import lepackage.models.dto.UtenteDTO;
import lepackage.exceptions.BusinessException;
import lepackage.dao.MateriaEFacoltaDao;
import lepackage.dao.UtenteDao;
import lepackage.models.entities.FacoltaEntity;
import lepackage.models.pojos.utilities.UtilityClass;

@Component
public class CommonService {

	private UtenteDao utenteDao;
	private MateriaEFacoltaDao materiaFacoltaDao;
	private UtilityClass<SuperDTO> utilityClass;

	public CommonService(UtenteDao utenteDao, MateriaEFacoltaDao materiaEFacoltaDao,
			UtilityClass<SuperDTO> utilityClass) {
		this.utenteDao = utenteDao;
		this.materiaFacoltaDao = materiaEFacoltaDao;
		this.utilityClass = utilityClass;
	}

	public ResponseDTO tryRegistrazioneUtente(UtenteDTO utenteDaRegistrare) throws Exception {
		try {
			System.out.println("Entra tryRegistrazioneUtente. Inizio controllo regex utenteDTO");
			utenteDaRegistrare.verificaNonNullitaCampiPerRegister();
			utilityClass.regexCheckUnoFinoAQuattroCampi(THREE_REGEX_ARGUMENTS, LOGIN_REGEX_MAIL,
					utenteDaRegistrare.getEmail(), LOGIN_REGEX_USR, 
					utenteDaRegistrare.getUsername(), LOGIN_REGEX_PSW,
					utenteDaRegistrare.getPassword(), null, null);
			
			System.out.println("Controllo ruolo.");
			if (utenteDaRegistrare.getRuolo() == null) {
				System.out.println("Lancio eccezione causa ruolo utenteDTO nullo.");
				throw new BusinessException("Il ruolo è nullo.");
			}
			System.out.println("Ruolo accettato, controllo facoltà.");
			
			if (utenteDaRegistrare.getFacoltaId() == null) {
				System.out.println("Lancio eccezione causa facolta_id utenteDTO nullo.");
				throw new BusinessException("facolta_id è nullo");
			}
			System.out.println("Tutti i controlli DTO superati, passo a chiamate DB.");
			
			FacoltaEntity facoltaTrovata = materiaFacoltaDao
					.findFacoltaConMaterieById(utenteDaRegistrare.getFacoltaId());
			if (facoltaTrovata.getMaterie() == null || facoltaTrovata.getMaterie().size() == 0) {
				System.out.println("Nessuna materia trovata nella facoltà cercata.");
				throw new BusinessException(
						"Nessuna materia trovata nella facoltà cercata a MateriaEFacoltaService findFacoltaConMaterieById.", 
						new ErrorDTO(HttpStatus.PARTIAL_CONTENT));
			}
			utenteDao.registraNuovoUtente(utenteDaRegistrare);
			UtenteDTO nuovoUtentePerFrontEnd = new UtenteDTO(utenteDaRegistrare.getUsername(), utenteDaRegistrare.getEmail(), utenteDaRegistrare.getRuolo());
			return new ResponseDTO("Utente registrato!", nuovoUtentePerFrontEnd, HttpStatus.OK);
		} catch (BusinessException e) {
			System.out.println("Lancio una BusinessException a UtenteService tryRegistrazioneUtente.");
			throw new BusinessException(e.getMessage() + " a UtenteService tryRegistrazioneUtente.");
		} catch (Exception e) {
			System.out.println("Lancio una Exception a UtenteService tryRegistrazioneUtente " + e.getMessage());
			throw e;
		}
	}
}
