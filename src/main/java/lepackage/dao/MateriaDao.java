package lepackage.dao;

import org.springframework.stereotype.Component;

import lepackage.models.dto.FacoltaUtenteDTO;
import lepackage.models.dto.UtenteMateriaDTO;
import lepackage.dao.interfaces.MateriaDaoInterface;
import lepackage.exceptions.BusinessException;
import lepackage.mappers.MateriaMapper;
import lepackage.models.entities.FacoltaEntity;
import lepackage.models.entities.MateriaEntity;
import lepackage.utils.SqlMapFactory;

@Component
public class MateriaDao implements MateriaDaoInterface {	

	@Override
	public MateriaEntity findMateriaConUtentiById(Integer materiaId) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			MateriaMapper materiaMapper = (MateriaMapper) SqlMapFactory.instance().getMapper(MateriaMapper.class);
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
			MateriaMapper materiaMapper = (MateriaMapper) SqlMapFactory.instance().getMapper(MateriaMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findFacoltaConMaterieById.");
			Integer righeDiUtenteConMaterieInserito = materiaMapper.insertMateriaEUtente(utenteConMaterie);
			System.out.println("Utente con materie inserito nella brideg table, " + righeDiUtenteConMaterieInserito + " record inseriti.");
			return righeDiUtenteConMaterieInserito;
		} catch (Exception e) {	
			System.out.println("Exception a insertMateriaEUtente " +  e.getMessage());
			throw new Exception(e.getStackTrace()[0] + "");
		} 
	}

}
