package lepackage.services;

import org.springframework.stereotype.Service;

import lepackage.dao.UtenteDao;
import lepackage.models.UtenteEntity;

@Service
public class UtenteService {

	private UtenteDao utenteDao;
	
	public UtenteService(UtenteDao utenteDao) {
		this.utenteDao = utenteDao;
	}
	
	public UtenteEntity findUtenteByUsername(String username) {
		try {
		return utenteDao.findUtenteByUsername(username);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
