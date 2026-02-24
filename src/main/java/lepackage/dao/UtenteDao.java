package lepackage.dao;

import org.springframework.stereotype.Component;

import lepackage.mappers.UtenteMapper;
import lepackage.models.UtenteEntity;
import lepackage.utils.SqlMapFactory;

@Component
public class UtenteDao implements UtenteMapper {
	
	private UtenteMapper utenteMapper = null;

	@Override
	public UtenteEntity findUtenteByUsername(String username) {	
		try {
	    SqlMapFactory.instance().openSession();
	    utenteMapper = (UtenteMapper)SqlMapFactory.instance().getMapper(UtenteMapper.class);
	    UtenteEntity utente = utenteMapper.findUtenteByUsername(username);
	    SqlMapFactory.instance().commitSession();
	    SqlMapFactory.instance().closeSession();
	    return utente;
		} catch (Exception e) {
			SqlMapFactory.instance().rollbackSession();
			throw e;
		}
		finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	
}
