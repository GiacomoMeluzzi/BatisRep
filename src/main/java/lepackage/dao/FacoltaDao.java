package lepackage.dao;

import org.springframework.stereotype.Component;

import lepackage.dao.interfaces.FacoltaDaoInterface;
import lepackage.exceptions.BusinessException;
import lepackage.mappers.FacoltaMapper;
import lepackage.mappers.MateriaMapper;
import lepackage.models.dto.FacoltaUtenteDTO;
import lepackage.models.entities.FacoltaEntity;
import lepackage.utils.SqlMapFactory;

@Component
public class FacoltaDao implements FacoltaDaoInterface {
	
	@Override
	public FacoltaEntity findFacoltaConMaterieById(Integer facoltaId) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			FacoltaMapper facoltaMapper = (FacoltaMapper) SqlMapFactory.instance().getMapper(MateriaMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findFacoltaConMaterieById.");
			FacoltaEntity facoltaTrovata = facoltaMapper.findFacoltaConMaterieById(facoltaId);
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
	public Integer insertFacoltaEUtente(FacoltaUtenteDTO facoltaConUtenti) throws Exception {
		try {
			facoltaConUtenti.verificaNonNullitaCampi();
			FacoltaMapper facoltaMapper = (FacoltaMapper) SqlMapFactory.instance().getMapper(MateriaMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findFacoltaConMaterieById.");
			Integer righeDiFacoltaConUtentiInserita = facoltaMapper.insertFacoltaEUtente(facoltaConUtenti);
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
