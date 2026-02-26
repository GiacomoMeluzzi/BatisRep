package lepackage.services;

import static lepackage.utilities.Constants.*;

import lepackage.dao.MateriaEFacoltaDao;
import lepackage.dao.UtenteDao;
import lepackage.dto.ResponseDTO;
import lepackage.dto.SuperDTO;
import lepackage.dto.UtenteDTO;
import lepackage.exceptions.BusinessException;
import lepackage.models.FacoltaEntity;
import lepackage.utilities.UtilityClass;

public class CommonService {

	private UtenteDao utenteDao;
	private MateriaEFacoltaDao materiaFacoltaDao;
	private UtenteService utenteService;
	private MateriaEFacoltaService materiaEFacoltaService;
	private UtilityClass<SuperDTO> utilityClass;
	
	public CommonService(UtenteDao utenteDao, MateriaEFacoltaDao materiaEFacoltaDao,
			UtenteService utenteService, MateriaEFacoltaService materiaEFacoltaService,
			UtilityClass<SuperDTO> utilityClass) {
		this.utenteDao = utenteDao;
		this.materiaFacoltaDao = materiaEFacoltaDao;
		this.utenteService = utenteService;
		this.materiaEFacoltaService = materiaEFacoltaService;
		this.utilityClass = utilityClass;
	}
	
	public ResponseDTO tryRegistrazioneUtente (UtenteDTO utenteDaRegistrare) throws Exception {
		try {
			System.out.println("Entra tryRegistrazioneUtente. Inizio controllo regex utenteDTO");
			utilityClass.regexCheckUnoFinoAQuattroCampi(THREE_REGEX_ARGUMENTS,
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
			if (utenteDaRegistrare.getFacoltaId() == null) {
				System.out.println("Lancio eccezione causa facolta_id utenteDTO nullo.");
				throw new BusinessException("facolta_id è nullo");
			}
			System.out.println("Facolta_id ok, controllo materie.");
			if (utenteDaRegistrare.getMaterie() == null || utenteDaRegistrare.getMaterie().size() == 0) {
				System.out.println("Lancio eccezione causa materie utenteDTO nullo o vuoto.");
				throw new BusinessException("materie utenteDTO nullo o vuoto");
			}
			System.out.println("Tutti i controlli DTO superati, passo a chiamate DB.");
			FacoltaEntity facoltaTrovata = materiaFacoltaDao.findFacoltaConMaterieById(utenteDaRegistrare.getFacoltaId());
			if (facoltaTrovata.getMaterie() == null || facoltaTrovata.getMaterie().size() == 0) {
				System.out.println("Nessuna materia trovata nella facoltà cercata.");
				throw new BusinessException(
						"Nessuna materia trovata nella facoltà cercata a MateriaEFacoltaService findFacoltaConMaterieById.");
			}
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
