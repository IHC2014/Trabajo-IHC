package cl.usach.diinf.revalora.grupo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import cl.usach.diinf.revalora.grupo.dto.GrupoDTO;
import cl.usach.diinf.revalora.grupo.entities.GrupoEntity;
import cl.usach.diinf.revalora.persona.dto.PersonaDTO;
import cl.usach.diinf.revalora.persona.entities.PersonaEntity;
import cl.usach.diinf.revalora.personas.util.PersonaUtil;

/**
 * <p>UserGroupDAO</p>
 *
 * Clase encargada del acceso a la capa de datos para las funciones
 * relevantes para una persona.
 *
 * @author Pablo Gavilan
 * @version 1.0
 *
 */
public class GrupoDAO implements GroupDAOImpl {

	/**
	 * Logger de la clase
	 * @since 1.0
	 */
	Logger log = Logger.getLogger(GrupoDAO.class);

	/**
	 * Objeto encargado de la conexion por jpa a la capa de datos
	 * @since 1.0
	 */
	private EntityManager entityManager;

	/**
	 * Constructor de la clase que instancia al #entityManager
	 * @since 1.0
	 */
	public GrupoDAO() {
		this.log.info("Crea constructor");
		try{
			this.entityManager = Persistence.createEntityManagerFactory("revalora-pu").createEntityManager();
		} catch (Exception e) {
			this.log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Metodo encargado de insertar una usergroup a la base de datos.
	 *
	 * @author Pablo Gavilan (07/05/2014).
	 * @param p UserGroup a agregar a la base de datos.
	 * @throws Exception exepcion lanzada al insertar la UserGroup.
	 * @since 1.0
	 */
	public void insertaUserGroup (GrupoDTO u) throws Exception {

		this.log.info("Ingresa metodo");
		GrupoEntity ue = this.transforma(u);
		try {
			this.log.info("Inicia transacion");
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(ue);
			this.entityManager.getTransaction().commit();
			this.log.info("Termina transacion");
		}catch(Exception e) {
			this.log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Metodo que retorna la lista de usergroups.
	 * @since 1.0
	 * @return Lista de objetos usergroups.
	 */
    public List<GrupoDTO> obtenerUserGroups() throws Exception{

		try {
			this.log.info("Antes de llamar createNamedQuery");
			List<GrupoEntity> luge = this.entityManager.createNamedQuery(GrupoEntity.SQL_SELECT_ALL, GrupoEntity.class).getResultList();

			List<GrupoDTO> lug = new ArrayList<GrupoDTO>();

			for (GrupoEntity userGroupEntitie : luge){
				lug.add(this.transforma(userGroupEntitie));
			}


			return lug;

		} catch(Exception e) {
			this.log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Clase encargada en eliminar a una usergroup
	 *
	 * @since 1.0
	 * @param p Objeto usergroup a eliminar.
	 * @throws Exception
	 *
	 */
	public void eliminaUserGroup(GrupoDTO u) throws Exception {
		this.log.info("Iniciando eliminausergroup");
		try {
			this.log.info("Antes de transformar UserGroup a entidad");
			GrupoEntity uge = this.transforma(u);
			this.log.info("Busca UserGroup para eliminar");
			uge = this.entityManager.find(GrupoEntity.class, uge.getId());
			this.log.info("Comienza transaccion");
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(uge);
			this.entityManager.getTransaction().commit();
			this.log.info("Termina transaccion");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	/**
	 * Clase encargada en actualziar a una UserGroup
	 *
	 * @since 1.0
	 * @param p Datos de objeto UserGroup a actualizar en la base de datos.
	 * @throws Exception
	 *
	 */
	public void actualizaUserGroup(GrupoDTO u) throws Exception {
		this.log.info("Iniciando actualziaUserGroup");
		try {
			this.log.info("Busca UserGroup para eliminar");
			GrupoEntity ue = this.entityManager.find(GrupoEntity.class, u.getId() );
			this.log.info("Comienza transaccion");
			this.entityManager.getTransaction().begin();
			this.log.info("Seteamos datos transaccion");
			ue.setId(u.getId());
			ue.setGroupName(u.getGroupName());

			this.log.info("Termino datos transaccion");
			this.entityManager.getTransaction().commit();
			this.log.info("Termina transaccion");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	/**
	 * Metodo que recive una UserGroup y lo transforma en su identidad
	 *
	 * @since 1.0
	 * @param p Objeto UserGroup a transformar en entidad
	 * @return entidad de tipo UserGroup
	 */
	private GrupoEntity transforma(GrupoDTO ug) {
		GrupoEntity uge = new GrupoEntity();
		uge.setId(ug.getId());
		uge.setGroupName(ug.getGroupName());
		return uge;
	}

	/**
	 * Metodo que recive una entidad UserGroup y lo transforma en su objeto
	 *
	 * @since 1.0
	 * @param p Entidad UserGroup a tranasforma.
	 * @return objeto UserGroup.
	 * UserGroup transforma(GrupoEntity p)
	 */
	private GrupoDTO transforma (GrupoEntity u)    {
		GrupoDTO ug = new GrupoDTO();
		ug.setId(u.getId());
		ug.setGroupName(u.getGroupName());
		return ug;
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
		this.log.info("Iniciando obtenerGrupo(String rut)");
		try {
			this.log.info("Busca grupo");
			GrupoEntity pe = this.entityManager.find(GrupoEntity.class, id);
			this.log.info("Termina transaccion y retorna");
			return this.transforma(pe);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
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
	public List<PersonaDTO> obtenerPersonasPorGrupo(String id) throws Exception {
		this.log.info("Iniciando obtenerGrupo(String rut)");
		try {
			this.log.info("Busca grupo");

			TypedQuery<PersonaEntity> query = this.entityManager.createQuery(
			        "SELECT c FROM PersonaEntity c WHERE c.id = ?1", PersonaEntity.class);
			query.setParameter(1, id);

			List<PersonaEntity> salida = query.getResultList();

			this.log.info("Termina transaccion y retorna");
			return PersonaUtil.transforma(salida);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}
}
