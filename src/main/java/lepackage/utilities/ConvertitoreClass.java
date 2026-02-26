package lepackage.utilities;

import java.util.ArrayList;
import java.util.List;

import lepackage.dto.FacoltaDTO;
import lepackage.dto.UtenteDTO;
import lepackage.models.FacoltaEntity;
import lepackage.models.MateriaEntity;
import lepackage.models.UtenteEntity;

public class ConvertitoreClass {
	
	public static UtenteDTO utenteDTOtoEntityNoPassword(UtenteEntity utenteDaConvertireSenzaPassword) {
		System.out.println("Inizio conversione DTO a entità.");
		UtenteDTO nuovoUtenteDTO = new UtenteDTO();
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
	}
	
	public static FacoltaDTO facoltaDTOtoEntity(FacoltaEntity facoltaDaConvertire) {
		System.out.println("Inizio conversione DTO a entità.");
		FacoltaDTO nuovaFacoltaDTO = new FacoltaDTO();
		nuovaFacoltaDTO.setId(facoltaDaConvertire.getId());
		nuovaFacoltaDTO.setNome(facoltaDaConvertire.getNome());
		if (facoltaDaConvertire.getMaterie() != null && 
				facoltaDaConvertire.getMaterie().size() != 0) {
			List<String> materiePerDto = new ArrayList<String>();
			for (MateriaEntity materiaDaConvertire : facoltaDaConvertire.getMaterie()) {
				materiePerDto.add(materiaDaConvertire.getNome());
			}
			nuovaFacoltaDTO.setMaterie(materiePerDto);
		}
		System.out.println("Oggetto facoltaEntity convertito in DTO.");
		return nuovaFacoltaDTO;
	}
	
}
