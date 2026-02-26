package lepackage.dao;

import org.springframework.stereotype.Component;

import lepackage.models.dto.FacoltaUtenteDTO;
import lepackage.models.dto.UtenteMateriaDTO;
import lepackage.exceptions.BusinessException;
import lepackage.mappers.MateriaEFacoltaMapper;
import lepackage.models.entities.FacoltaEntity;
import lepackage.models.entities.MateriaEntity;
import lepackage.utils.SqlMapFactory;

@Component
public class MateriaEFacoltaDao implements MateriaEFacoltaMapper {
	
	private MateriaEFacoltaMapper materiaMapper = null;

	@Override
	public FacoltaEntity findFacoltaConMaterieById(Integer facoltaId) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			materiaMapper = (MateriaEFacoltaMapper) SqlMapFactory.instance().getMapper(MateriaEFacoltaMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findFacoltaConMaterieById.");
			FacoltaEntity facoltaTrovata = materiaMapper.findFacoltaConMaterieById(facoltaId);
			if (null == facoltaTrovata) {
				System.out.println("Facoltà non trovata.");
				throw new BusinessException("Facoltà non trovata.");
			}
			SqlMapFactory.instance().commitSession();
			System.out.println("Facoltà trovata nel DB.");
			return facoltaTrovata;
		} catch (BusinessException e) {
			System.out.println("BusinessException a findFacoltaConMaterieById " + e.getMessage());
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} catch (Exception e) {	
			System.out.println("Exception a findFacoltaConMaterieById " + e.getMessage());
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}
	
	@Override
	public MateriaEntity findMateriaConUtentiById(Integer materiaId) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			materiaMapper = (MateriaEFacoltaMapper) SqlMapFactory.instance().getMapper(MateriaEFacoltaMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findMateriaConUtentiById.");
			MateriaEntity materiaTrovata = materiaMapper.findMateriaConUtentiById(materiaId);
			if (null == materiaTrovata) {
				System.out.println("Materia non trovata.");
				throw new BusinessException("Materia non trovata.");
			}
			SqlMapFactory.instance().commitSession();
			System.out.println("Materia trovata nel DB.");
			return materiaTrovata;
		} catch (BusinessException e) {
			System.out.println("BusinessException a findMateriaConUtentiById " + e.getMessage());
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} catch (Exception e) {	
			System.out.println("Exception a findMateriaConUtentiById " + e.getMessage());
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	@Override
	public Integer insertMateriaEUtente(UtenteMateriaDTO utenteConMaterie) throws Exception {
		try {
			utenteConMaterie.verificaNonNullitaCampi();
			materiaMapper = (MateriaEFacoltaMapper) SqlMapFactory.instance().getMapper(MateriaEFacoltaMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findFacoltaConMaterieById.");
			Integer righeDiUtenteConMaterieInserito = materiaMapper.insertMateriaEUtente(utenteConMaterie);
			if (righeDiUtenteConMaterieInserito == null || righeDiUtenteConMaterieInserito == 0) {
				System.out.println("Errore nel'inserimento dell'utente con materie in birdge table.");
				throw new BusinessException("Errore nel'inserimento dell'utente con materie in birdge table.");
			}
			System.out.println("Utente con materie inserito nella brideg table, " + righeDiUtenteConMaterieInserito + " record inseriti.");
			return righeDiUtenteConMaterieInserito;
		} catch (BusinessException e) {
			System.out.println("BusinessException a insertMateriaEUtente.");
			throw e;
		} catch (Exception e) {	
			System.out.println("Exception a insertMateriaEUtente " +  e.getMessage());
			throw new Exception(e.getStackTrace()[0] + "");
		} 
	}

	@Override
	public Integer insertFacoltaEUtente(FacoltaUtenteDTO facoltaConUtenti) throws Exception {
		try {
			facoltaConUtenti.verificaNonNullitaCampi();
			materiaMapper = (MateriaEFacoltaMapper) SqlMapFactory.instance().getMapper(MateriaEFacoltaMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findFacoltaConMaterieById.");
			Integer righeDiFacoltaConUtentiInserita = materiaMapper.insertFacoltaEUtente(facoltaConUtenti);
			if (righeDiFacoltaConUtentiInserita == null || righeDiFacoltaConUtentiInserita == 0) {
				System.out.println("Errore nel'inserimento della facoltà con utenti in birdge table.");
				throw new BusinessException("Errore nel'inserimento della facoltà con utenti in birdge table.");
			}
			System.out.println("Facoltà con utenti inserita nella bridge table, " + righeDiFacoltaConUtentiInserita + " record inseriti.");
			return righeDiFacoltaConUtentiInserita;
		} catch (BusinessException e) {
			System.out.println("BusinessException a insertFacoltaEUtente.");
			throw e;
		} catch (Exception e) {	
			System.out.println("Exception a insertFacoltaEUtente " +  e.getMessage());
			throw new Exception(e.getStackTrace()[0] + "");
		}
	}

}
