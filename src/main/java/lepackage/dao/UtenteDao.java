package lepackage.dao;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lepackage.exceptions.BusinessException;
import lepackage.mappers.UtenteMapper;
import lepackage.models.UtenteEntity;
import lepackage.utils.SqlMapFactory;

@Component
public class UtenteDao implements UtenteMapper {

	private UtenteMapper utenteMapper = null;

	@Override
	public UtenteEntity findUtenteByUsername(String usernameInEntrata) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			utenteMapper = (UtenteMapper) SqlMapFactory.instance().getMapper(UtenteMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findUtenteByUsername.");
			UtenteEntity utenteTrovato = utenteMapper.findUtenteByUsername(usernameInEntrata);
			if (null == utenteTrovato) {
				System.out.println("Utente non trovato");
				throw new BusinessException("Utente non trovato.");
			}
			SqlMapFactory.instance().commitSession();
			System.out.println("Utente trovato nel DB.");
			return utenteTrovato;
		} catch (BusinessException e) {
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} catch (Exception e) {	
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	@Override
	public UtenteEntity findUtenteByEmailEPassword(String emailInEntrata, String passwordInEntrata) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			utenteMapper = (UtenteMapper) SqlMapFactory.instance().getMapper(UtenteMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findUtenteByEmailEPassword.");
			UtenteEntity utenteTrovato = utenteMapper.findUtenteByEmailEPassword(emailInEntrata, passwordInEntrata);
			if (null == utenteTrovato) {
				System.out.println("Utente non trovato");
				throw new BusinessException("Utente non trovato.", HttpStatus.FORBIDDEN);
			}
			SqlMapFactory.instance().commitSession();
			System.out.println("Utente trovato nel DB.");
			return utenteTrovato;
		} catch (BusinessException e) {
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} catch (Exception e) {	
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}
	
	@Override
	public UtenteEntity findUtenteByEmailEPasswordJoinRuolo(String emailInEntrata, String passwordInEntrata) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			utenteMapper = (UtenteMapper) SqlMapFactory.instance().getMapper(UtenteMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findUtenteByEmailEPassword.");
			UtenteEntity utenteTrovato = utenteMapper.findUtenteByEmailEPasswordJoinRuolo(emailInEntrata, passwordInEntrata);
			if (null == utenteTrovato) {
				System.out.println("Utente non trovato");
				throw new BusinessException("Utente non trovato.", HttpStatus.FORBIDDEN);
			}
			SqlMapFactory.instance().commitSession();
			System.out.println("Utente trovato nel DB.");
			return utenteTrovato;
		} catch (BusinessException e) {
			SqlMapFactory.instance().rollbackSession();
			throw e;
		} catch (Exception e) {	
			SqlMapFactory.instance().rollbackSession();
			throw new Exception(e.getStackTrace()[0] + "");
		} finally {
			SqlMapFactory.instance().closeSession();
		}
	}

	@Override
	public UtenteEntity findUtenteConMaterieDaUsername(String usernameInEntrata) throws Exception {
		try {
			SqlMapFactory.instance().openSession();
			utenteMapper = (UtenteMapper) SqlMapFactory.instance().getMapper(UtenteMapper.class);
			System.out.println("Aperta istanza SqlMapFactory, inizio query a findUtenteConMaterieDaUsername.");
			UtenteEntity utenteTrovato = utenteMapper.findUtenteConMaterieDaUsername(usernameInEntrata);
			if (null == utenteTrovato) {
				System.out.println("Utente non trovato");
				throw new BusinessException("Utente non trovato.", HttpStatus.FORBIDDEN);
			}
			SqlMapFactory.instance().commitSession();
			System.out.println("Utente trovato nel DB.");
			return utenteTrovato;
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
