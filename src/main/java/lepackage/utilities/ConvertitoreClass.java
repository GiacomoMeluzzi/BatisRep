package lepackage.utilities;

import java.util.ArrayList;
import java.util.List;

import lepackage.dto.FacoltaDTO;
import lepackage.dto.MateriaDTO;
import lepackage.dto.UtenteDTO;
import lepackage.exceptions.BusinessException;
import lepackage.models.FacoltaEntity;
import lepackage.models.MateriaEntity;
import lepackage.models.UtenteEntity;

public class ConvertitoreClass {

	public static UtenteDTO utenteEntityToDtoNoFacoltaEMaterie(UtenteEntity utenteDaConvertireSenzaPassword) throws Exception {
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
			if(utenteDaConvertireSenzaPassword.getRuolo() != null) {
				nuovoUtenteDTO.setRuolo(utenteDaConvertireSenzaPassword.getRuolo().getNome());
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
				nuovoUtenteDTO.setRuolo(utenteDaConvertireSenzaPassword.getRuolo().getNome());
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

}
