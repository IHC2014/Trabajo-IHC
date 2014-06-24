package cl.usach.diinf.revalora.grupo.dto;

import java.io.Serializable;
import java.util.List;

import cl.usach.diinf.revalora.persona.dto.PersonaDTO;

public class GrupoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String groupName;
	private List<PersonaDTO> personas;

	public GrupoDTO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<PersonaDTO> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<PersonaDTO> personas) {
		this.personas = personas;
	}



}
