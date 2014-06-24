package cl.usach.diinf.revalora.grupo;

import java.util.List;

import javax.ejb.Remote;

import cl.usach.diinf.revalora.grupo.dto.GrupoDTO;

@Remote
public interface GrupoImpl {

	/**
	 * @see GrupoBean#insertarUserGroup(GrupoDTO)
	 * @param u
	 * @throws Exception
	 */
	public void insertarUserGroup(GrupoDTO u) throws Exception;

	/**
	 * @see GrupoBean#actualizaUserGroup(GrupoDTO)
	 * @param u
	 * @throws Exception
	 */
	public void actualizaUserGroup(GrupoDTO u) throws Exception;

	/**
	 * @see GrupoBean#obtenerUserGroups()
	 * @return
	 * @throws Exception
	 */
	public List<GrupoDTO> obtenerUserGroups() throws Exception;

	/**
	 * @see GrupoBean#obtenerUserGroups(String)
	 * @return
	 * @throws Exception
	 */
	public GrupoDTO obtenerUserGroups(String id) throws Exception;

	/**
	 * @see GrupoBean#eliminaUserGroup(GrupoDTO)
	 * @param u
	 * @throws Exception
	 */
	public void eliminaUserGroup(GrupoDTO u) throws Exception;

}
