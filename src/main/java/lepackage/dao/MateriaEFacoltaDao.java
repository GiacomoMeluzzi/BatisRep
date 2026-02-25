package lepackage.dao;

import org.springframework.stereotype.Component;

import lepackage.exceptions.BusinessException;
import lepackage.mappers.MateriaEFacoltaMapper;
import lepackage.models.FacoltaEntity;
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

}
