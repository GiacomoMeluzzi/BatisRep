package lepackage.dao;

import org.springframework.stereotype.Component;

import lepackage.exceptions.BusinessException;
import lepackage.mappers.VotoMapper;
import lepackage.models.dto.VotoDTO;
import lepackage.models.entities.VotoEntity;
import lepackage.utils.SqlMapFactory;

@Component
public class VotoDao implements VotoMapper {

	VotoMapper votoMapper = null;
	
	@Override
	public VotoEntity selectVotiPerProfessoreEStudenteById(VotoDTO dtoConUtentiDaCercare) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			votoMapper = (VotoMapper) SqlMapFactory.instance().getMapper(VotoMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a selectVotiPerProfessoreEStudenteById.");
			VotoEntity utenteTrovato = votoMapper.selectVotiPerProfessoreEStudenteById(dtoConUtentiDaCercare);
			if (null == utenteTrovato) {
				System.out.println("Voti non trovati");
				throw new BusinessException("Voti non trovati.");
			}
			SqlMapFactory.instance().commitSession();
			System.out.println("Voti trovati nel DB.");
			return utenteTrovato;
		} catch (BusinessException e) {
			System.out.println("Lancio BusinessException a selectVotiPerProfessoreEStudenteById" + e.getMessage());
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} catch (Exception e) {
			System.out.println("Lancio Exception a selectVotiPerProfessoreEStudenteById" + e.getMessage());
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	
}
