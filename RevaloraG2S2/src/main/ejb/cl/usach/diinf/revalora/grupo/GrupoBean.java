package cl.usach.diinf.revalora.grupo;

import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import cl.usach.diinf.revalora.grupo.dao.GroupDAOImpl;
import cl.usach.diinf.revalora.grupo.dao.GrupoDAO;
import cl.usach.diinf.revalora.grupo.dto.GrupoDTO;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;

@Stateless
public class GrupoBean implements GrupoImpl {

	/**
	 * Logger de la clase
	 *
	 * @since 1.0
	 */
	Logger log = Logger.getLogger(GrupoBean.class);

	/**
	 * Objeto de conexion a la clase de acceso de datos para UserGroup
	 *
	 * @since 1.0
	 */
	GroupDAOImpl dao;

	/**
	 * Constructor de la clase
	 *
	 * @since 1.0
	 */
	public GrupoBean() {
		this.log.info("Instancia EJB");
		this.dao = new GrupoDAO();
	}

	/**
	 * Método encargado de ingresar a la base de datos una UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup ingresada a la base de datos.
	 * @throws Exception
	 * @since 1.0
	 */
	public void insertarUserGroup(GrupoDTO u) throws Exception {
		try {
			this.log.info("Antes de insertar");
			this.dao.insertaUserGroup(u);
			this.log.info("Despues de insertar");
		} catch (Exception e) {
			this.log.error("Error en insertarPersoa");
			this.log.error("UserGroup " + u.toString());
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de actualziar a la base de datos una UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup ingresada a la base de datos.
	 * @throws Exception
	 * @since 1.0
	 */
	public void actualizaUserGroup(GrupoDTO u) throws Exception {
		try {
			this.log.info("Antes de actualizaUserGroup");
			this.dao.actualizaUserGroup(u);
			;
			this.log.info("Despues de actualizaUserGroup");
		} catch (Exception e) {
			this.log.error("Error en actualizaUserGroup");
			this.log.error("UserGroup " + u.toString());
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de elimina en la base de datos una UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup que se elimina.
	 * @return
	 * @throws Exception
	 * @since 1.0
	 */
	public List<GrupoDTO> obtenerUserGroups() throws Exception {
		try {
			this.log.info("Antes de obtenerUserGroups");
			return this.dao.obtenerUserGroups();
		} catch (Exception e) {
			this.log.error("Error en obtenerUserGroups");
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

	/**
	 * Método encargado de obtener en la base de datos un UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup que se elimina.
	 * @return GrupoDTO segun su llave
	 * @throws Exception
	 * @author Pablo Gavilan
	 * @since 1.0
	 */
	public GrupoDTO obtenerUserGroups(String id) throws Exception {
		try {
			this.log.info("Antes de obtenerPersonas");
			GrupoDTO salida = this.dao.obtenerUserGroups(id);
			List<PersonaDTO> personas = this.dao.obtenerPersonasPorGrupo(id);
			salida.setPersonas(personas);
			return salida;
		} catch (Exception e) {
			this.log.error("Error en obtenerGrupo");
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}
	/**
	 * Método encargado de elimina en la base de datos una UserGroup.
	 *
	 * @param p
	 *            Objeto UserGroup que se elimina.
	 * @throws Exception
	 * @since 1.0
	 */
	public void eliminaUserGroup(GrupoDTO u) throws Exception {
		try {
			this.log.info("Antes de eliminaUserGroup");
			this.dao.eliminaUserGroup(u);
			this.log.info("Despues de eliminaUserGroup");
		} catch (Exception e) {
			this.log.error("Error en eliminaUserGroup");
			this.log.error("UserGroup " + u.toString());
			this.log.error("Error:" + e.getMessage());
			this.log.error("Error:" + e.getCause());
			this.log.error("Error:" + e.getClass());
			throw e;
		}
	}

}
