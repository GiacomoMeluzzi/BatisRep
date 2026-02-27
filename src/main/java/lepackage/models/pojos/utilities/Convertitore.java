package lepackage.models.pojos.utilities;

import java.util.ArrayList;
import java.util.List;

import lepackage.models.dto.FacoltaDTO;
import lepackage.models.dto.MateriaDTO;
import lepackage.models.dto.UtenteDTO;
import lepackage.exceptions.BusinessException;
import lepackage.models.entities.FacoltaEntity;
import lepackage.models.entities.MateriaEntity;
import lepackage.models.entities.RuoloEntity;
import lepackage.models.entities.UtenteEntity;
import lepackage.varie.Ruolo;

public class Convertitore {

	public static UtenteDTO utenteEntityToDtoNoFacoltaEMaterie(UtenteEntity utenteDaConvertireSenzaPassword)
			throws Exception {
		try {
			System.out.println("Inizio conversione DTO a entità.");
			UtenteDTO nuovoUtenteDTO = new UtenteDTO();
			if (utenteDaConvertireSenzaPassword.getId() == null || utenteDaConvertireSenzaPassword.getUsername() == null
					|| utenteDaConvertireSenzaPassword.getEmail() == null) {
				throw new BusinessException("Campi fondamentali nella classe DTO vuoti.");
			}
			nuovoUtenteDTO.setId(utenteDaConvertireSenzaPassword.getId());
			nuovoUtenteDTO.setUsername(utenteDaConvertireSenzaPassword.getUsername());
			nuovoUtenteDTO.setEmail(utenteDaConvertireSenzaPassword.getEmail());
			nuovoUtenteDTO.setPassword(null);
			if (utenteDaConvertireSenzaPassword.getRuolo() != null) {
				nuovoUtenteDTO.setRuolo(utenteDaConvertireSenzaPassword.getRuolo().getNome().toString());
			}
			System.out.println("Convertita entità in DTO.");
			return nuovoUtenteDTO;
		} catch (Exception e) {
			System.out.println("Eccezione in covertitore utenteEntityToDTOnoConstraints");
			throw e;
		}
	}

	public static UtenteDTO utenteEntityToDTO(UtenteEntity utenteDaConvertireSenzaPassword) throws Exception {
		try {
			System.out.println("Inizio conversione DTO a entità.");
			UtenteDTO nuovoUtenteDTO = new UtenteDTO();
			if (utenteDaConvertireSenzaPassword.getId() == null || utenteDaConvertireSenzaPassword.getUsername() == null
					|| utenteDaConvertireSenzaPassword.getEmail() == null
					|| utenteDaConvertireSenzaPassword.getFacolta_id() == null) {
				throw new BusinessException("Campi fondamentali nella classe DTO vuoti.");
			}
			nuovoUtenteDTO.setId(utenteDaConvertireSenzaPassword.getId());
			nuovoUtenteDTO.setUsername(utenteDaConvertireSenzaPassword.getUsername());
			nuovoUtenteDTO.setEmail(utenteDaConvertireSenzaPassword.getEmail());
			nuovoUtenteDTO.setPassword(null);
			nuovoUtenteDTO.setFacoltaId(utenteDaConvertireSenzaPassword.getFacolta_id());
			if (null != utenteDaConvertireSenzaPassword.getRuolo()) {
				nuovoUtenteDTO.setRuolo(utenteDaConvertireSenzaPassword.getRuolo().getNome().toString());
			}
			if (utenteDaConvertireSenzaPassword.getMaterie() != null
					&& utenteDaConvertireSenzaPassword.getMaterie().size() != 0) {
				List<String> nomiMaterie = new ArrayList<String>();
				for (MateriaEntity materieDaConvertire : utenteDaConvertireSenzaPassword.getMaterie()) {
					nomiMaterie.add(materieDaConvertire.getNome());
				}
				nuovoUtenteDTO.setMaterie(nomiMaterie);
			}
			System.out.println("Convertita entità in DTO.");
			return nuovoUtenteDTO;
		} catch (Exception e) {
			System.out.println("Eccezione in covertitore utenteDTOtoEntityNoPassword");
			throw e;
		}
	}

	public static UtenteEntity utenteDTOtoEntityPerRegistrazione(UtenteDTO utenteDaConvertire) throws Exception {
		try {
			System.out.println("Inizio conversione DTO a entità.");
			UtenteEntity nuovoUtenteEntity = new UtenteEntity();

			if (utenteDaConvertire.getUsername() == null
					|| utenteDaConvertire.getEmail() == null || utenteDaConvertire.getPassword() == null) {
				throw new BusinessException("Campi fondamentali nella classe DTO vuoti.");
			}

			nuovoUtenteEntity.setUsername(utenteDaConvertire.getUsername());
			nuovoUtenteEntity.setEmail(utenteDaConvertire.getEmail());
			nuovoUtenteEntity.setPassword(utenteDaConvertire.getPassword());

			if (utenteDaConvertire.getFacoltaId() == null) {
				throw new BusinessException("FacoltaId nella classe DTO vuota.");
			}
			nuovoUtenteEntity.setFacolta_id(utenteDaConvertire.getFacoltaId());

			if (utenteDaConvertire.getRuolo() == null) {
				throw new BusinessException("Ruolo nella classe DTO è nullo.");
			}

			try {
				Ruolo ruoloEnum = Ruolo.valueOf(utenteDaConvertire.getRuolo().toUpperCase());
				nuovoUtenteEntity.setRuolo(new RuoloEntity(ruoloEnum));
			} catch (IllegalArgumentException e) {
				throw new BusinessException("Ruolo nella classe DTO è invalido.");
			}

			if (utenteDaConvertire.getMaterieId() == null || utenteDaConvertire.getMaterieId().size() == 0) {
				throw new BusinessException("Materie vuote nell'oggetto DTO.");
			}
			List<Integer> materieId = new ArrayList<Integer>();
			for (Integer materiaIdDaConvertire : utenteDaConvertire.getMaterieId()) {
				materieId.add(materiaIdDaConvertire);
			}
			nuovoUtenteEntity.setMaterieId(materieId);

			System.out.println("Oggetto utenteDTO convertito in Entity.");
			return nuovoUtenteEntity;

		} catch (Exception e) {
			System.out.println("Eccezione in covertitore utenteDTOtoEntityPerRegistrazione");
			throw e;
		}
	}

	public static FacoltaDTO facoltaEntityToDTO(FacoltaEntity facoltaDaConvertire) throws Exception {
		try {
			System.out.println("Inizio conversione DTO a entità.");
			FacoltaDTO nuovaFacoltaDTO = new FacoltaDTO();
			if (facoltaDaConvertire.getId() == null || facoltaDaConvertire.getNome() == null) {
				throw new BusinessException("Campi fondamentali nella classe DTO vuoti.");
			}
			nuovaFacoltaDTO.setId(facoltaDaConvertire.getId());
			nuovaFacoltaDTO.setNome(facoltaDaConvertire.getNome());
			if (facoltaDaConvertire.getMaterie() == null || facoltaDaConvertire.getMaterie().size() == 0) {
				System.out.println("Attenzione, l'oggetto facoltaDTO restituito non ha utenti.");
				return nuovaFacoltaDTO;
			}
			List<String> materiePerDto = new ArrayList<String>();
			for (MateriaEntity materiaDaConvertire : facoltaDaConvertire.getMaterie()) {
				materiePerDto.add(materiaDaConvertire.getNome());
			}
			nuovaFacoltaDTO.setMaterie(materiePerDto);
			System.out.println("Oggetto facoltaEntity convertito in DTO.");
			return nuovaFacoltaDTO;
		} catch (Exception e) {
			System.out.println("Eccezione in covertitore facoltaEntityToDTO");
			throw e;
		}
	}

	public static MateriaDTO materiaEntityToDTO(MateriaEntity materiaDaConvertire) throws Exception {
		try {
			System.out.println("Inizio conversione DTO a entità.");
			MateriaDTO nuovaMateriaDTO = new MateriaDTO();
			if (materiaDaConvertire.getId() == null || materiaDaConvertire.getNome() == null) {
				throw new BusinessException("Campi fondamentali nella classe DTO vuoti.");
			}
			nuovaMateriaDTO.setId(materiaDaConvertire.getId());
			nuovaMateriaDTO.setNome(materiaDaConvertire.getNome());
			if (materiaDaConvertire.getUtenti() == null || materiaDaConvertire.getUtenti().size() == 0) {
				System.out.println("Attenzione, l'oggetto materiaDTO restituito non ha utenti.");
				return nuovaMateriaDTO;
			}
			List<String> utentiPerDto = new ArrayList<String>();
			for (UtenteEntity utenteDaConvertire : materiaDaConvertire.getUtenti()) {
				utentiPerDto.add(utenteDaConvertire.getUsername());
			}
			nuovaMateriaDTO.setUtenti(utentiPerDto);
			System.out.println("Oggetto materiaEntity convertito in DTO.");
			return nuovaMateriaDTO;
		} catch (Exception e) {
			System.out.println("Eccezione in covertitore materiaEntityToDTO");
			throw e;
		}
	}

	public FacoltaEntity facoltaDTOtoEntity(FacoltaDTO facoltaDaConvertire) throws Exception {
		try {
			System.out.println("Inizio conversione DTO a entità.");
			FacoltaEntity nuovaFacoltaEntity = new FacoltaEntity();
			if (facoltaDaConvertire.getId() == null || facoltaDaConvertire.getNome() == null) {
				throw new BusinessException("Campi fondamentali nella classe DTO vuoti.");
			}
			nuovaFacoltaEntity.setId(facoltaDaConvertire.getId());
			nuovaFacoltaEntity.setNome(facoltaDaConvertire.getNome());
			if (facoltaDaConvertire.getUtentiId() != null && facoltaDaConvertire.getUtentiId().size() != 0) {
				List<Integer> utentiId = new ArrayList<Integer>();
				for (Integer utenteIdDaConvertire : facoltaDaConvertire.getUtentiId()) {
					utentiId.add(utenteIdDaConvertire);
				}
				nuovaFacoltaEntity.setUtentiId(utentiId);
			}
			if (facoltaDaConvertire.getMaterie() != null && facoltaDaConvertire.getMaterieId().size() != 0) {
				List<Integer> materieId = new ArrayList<Integer>();
				for (Integer materiaIdDaConvertire : facoltaDaConvertire.getMaterieId()) {
					materieId.add(materiaIdDaConvertire);
				}
				nuovaFacoltaEntity.setUtentiId(materieId);
			}
			System.out.println("Oggetto materiaEntity convertito in DTO.");
			return nuovaFacoltaEntity;
		} catch (Exception e) {
			System.out.println("Eccezione in covertitore materiaEntityToDTO");
			throw e;
		}
	}

	public MateriaEntity materiaDTOtoEntity(MateriaDTO materiaDaConvertire) throws Exception {
		try {
			System.out.println("Inizio conversione DTO a entità.");
			MateriaEntity nuovaMateriaEntity = new MateriaEntity();
			if (materiaDaConvertire.getId() == null || materiaDaConvertire.getNome() == null) {
				throw new BusinessException("Campi fondamentali nella classe DTO vuoti.");
			}
			nuovaMateriaEntity.setId(materiaDaConvertire.getId());
			nuovaMateriaEntity.setNome(materiaDaConvertire.getNome());
			if (materiaDaConvertire.getUtentiId() != null && materiaDaConvertire.getUtentiId().size() != 0) {
				List<Integer> utentiId = new ArrayList<Integer>();
				for (Integer utenteIdDaConvertire : materiaDaConvertire.getUtentiId()) {
					utentiId.add(utenteIdDaConvertire);
				}
				nuovaMateriaEntity.setUtentiId(utentiId);
			}
			System.out.println("Oggetto materiaEntity convertito in DTO.");
			return nuovaMateriaEntity;
		} catch (Exception e) {
			System.out.println("Eccezione in covertitore materiaEntityToDTO");
			throw e;
		}
	}

}
