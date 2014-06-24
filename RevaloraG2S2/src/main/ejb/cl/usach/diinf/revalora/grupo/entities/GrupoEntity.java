package cl.usach.diinf.revalora.grupo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the USER_GROUPS database table.
 *
 */
@Entity
@Table(name="USER_GROUPS")
@NamedQuery(name="UserGroupEntitie.findAll", query="SELECT u FROM GrupoEntity u")
public class GrupoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String SQL_SELECT_ALL = "UserGroupEntitie.findAll";

	@Id
	@Column(name="ID")
	private int id;

	@Column(name="GROUP_NAME")
	private String groupName;

	public GrupoEntity() {
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

}