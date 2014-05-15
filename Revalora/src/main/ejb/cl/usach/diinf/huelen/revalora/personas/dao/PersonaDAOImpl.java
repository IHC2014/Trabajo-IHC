package cl.usach.diinf.huelen.revalora.personas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import cl.usach.diinf.huelen.revalora.personas.dto.PersonaDTO;
import cl.usach.diinf.huelen.revalora.personas.entities.PersonaEntity;

/**
 * <p>
 * PersonaDAOImpl
 * </p>
 * 
 * Clase encargada del acceso a la capa de datos para las funciones relevantes
 * para una persona.
 * 
 * @author Pablo Gavilan
 * @updated Pablo Gavilan
 * @version 1.0
 * 
 */
public class PersonaDAOImpl implements PersonaDAO {

	/**
	 * Logger de la clase
	 * 
	 * @since 1.0
	 */
	Logger log = Logger.getLogger(PersonaDAOImpl.class);

	/**
	 * Objeto encargado de la conexion por jpa a la capa de datos
	 * 
	 * @since 1.0
	 */
	private EntityManager entityManager;

	/**
	 * Constructor de la clase que instancia al #entityManager
	 * 
	 * @author Pablo Gavilan
	 * @version 1.0
	 * @since 1.0
	 */
	public PersonaDAOImpl() {
		this.log.info("Crea constructor");
		try {
			this.entityManager = Persistence.createEntityManagerFactory(
					"revalora-pu").createEntityManager();
		} catch (Exception e) {
			this.log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Método encargado de obtener una lista de todas las personas.
	 * 
	 * @author Pablo Gavilan
	 * @return Lista de personas
	 * @throws Exception
	 *             generada al listar personas
	 * @version 1.0
	 * @since 1.0
	 */
	public List<PersonaDTO> obtenerPersonas() throws Exception {

		try {
			this.log.info("Antes de llamar createNamedQuery");
			List<PersonaEntity> lpe = this.entityManager.createNamedQuery(
					PersonaEntity.SQL_SELECT_ALL, PersonaEntity.class)
					.getResultList();

			List<PersonaDTO> lp = new ArrayList<PersonaDTO>();

			for (PersonaEntity personaEntitie : lpe) {
				lp.add(this.transforma(personaEntitie));
			}

			return lp;

		} catch (Exception e) {
			this.log.error("Error " + e);
			throw e;
		}
	}

	/**
	 * Metodo encargado de llamar al EJB para insertar una persona.
	 * 
	 * @author Pablo Gavilan
	 * @version 1.0
	 * @return Palabra clave que retorna a la pagina segun faces-config
	 * @throws Exception
	 *             error al eliminar una persona
	 * @since 1.0
	 */
	public void eliminaPersona(PersonaDTO p) throws Exception {
		this.log.info("Iniciando eliminaPErsona");
		try {
			this.log.info("Antes de transformar persona a entidad");
			PersonaEntity pe = this.transforma(p);
			this.log.info("Busca persona para eliminar");
			pe = this.entityManager.find(PersonaEntity.class, pe.getRut());
			this.log.info("Comienza transaccion");
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(pe);
			this.entityManager.getTransaction().commit();
			this.log.info("Termina transaccion");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	/**
	 * Método encargado de obtener una persona segun su rut.
	 * 
	 * @author Pablo Gavilan
	 * @param rut
	 *            , rut a retornar como objeto persona
	 * @return un objeto persona obtenido por su rut
	 * @throws Exception
	 *             generada al obtener una persona
	 * @version 1.0
	 * @since 1.0
	 */
	public PersonaDTO obtenerPersonas(String rut) throws Exception {
		this.log.info("Iniciando obtenerPersonas(String rut)");
		try {
			this.log.info("Busca persona para eliminar");
			PersonaEntity pe = this.entityManager
					.find(PersonaEntity.class, rut);
			this.log.info("Termina transaccion y retorna");
			return this.transforma(pe);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	/**
	 * Método encargado de actualziar a la base de datos una persona.
	 * 
	 * @author Pablo Gavilan
	 * @param p
	 *            Objeto persona ingresada a la base de datos.
	 * @throws Exception
	 *             generada al actualizar una persona
	 * @version 1.0
	 * @since 1.0
	 */
	public void actualizaPersona(PersonaDTO p) throws Exception {
		this.log.info("Iniciando actualziaPersona/insertar");
		try {
			this.log.info("Busca persona para editar");
			PersonaEntity pe = this.entityManager.find(PersonaEntity.class,
					p.getRut());

			if (pe != null && pe.getRut() != null
					&& pe.getRut().trim().compareTo("") != 0) {
				this.log.info("Comienza transaccion editar");
				this.entityManager.getTransaction().begin();
				this.log.info("Seteamos datos transaccion");
				pe.setApellido(p.getApellido());
				pe.setApellido(p.getApellido());
				pe.setClave(p.getClave());
				pe.setCorreo(p.getCorreo());
				pe.setCumpleano(p.getCumpleano());
				pe.setDireccion(p.getDireccion());
				pe.setGenero(p.getGenero());
				pe.setNombre(p.getNombre());
				pe.setPosicion(p.getPosicion());
				pe.setRut(p.getRut());
				pe.setTelefono(p.getTelefono());
				this.log.info("Termino datos transaccion");
				this.entityManager.getTransaction().commit();
				this.log.info("Termina transaccion editar");
			} else {
				this.log.info("Ingresa metodo");
				pe = this.transforma(p);
				try {
					this.log.info("Persona no existe comenzamos a crear");
					this.entityManager.getTransaction().begin();
					this.entityManager.persist(pe);
					this.entityManager.getTransaction().commit();
					this.log.info("Termina transacion crear");
				} catch (Exception e) {
					this.log.error("Error " + e);
					throw e;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error e:" + e);
		}
	}

	/**
	 * Metodo que recive una persona y lo transforma en su identidad
	 * 
	 * @author Pablo Gavilan
	 * @param p
	 *            Objeto persona a transformar en entidad
	 * @return entidad de tipo persona
	 * @version 1.0
	 * @since 1.0
	 */
	private PersonaEntity transforma(PersonaDTO p) {
		PersonaEntity pe = new PersonaEntity();
		pe.setApellido(p.getApellido());
		pe.setClave(p.getClave());
		pe.setCorreo(p.getCorreo());
		pe.setCumpleano(p.getCumpleano());
		pe.setDireccion(p.getDireccion());
		pe.setGenero(p.getGenero());
		pe.setId(p.getId());
		pe.setNombre(p.getNombre());
		pe.setPosicion(p.getPosicion());
		pe.setRut(p.getRut());
		pe.setTelefono(p.getTelefono());
		return pe;
	}

	/**
	 * Metodo que recive una entidad persona y lo transforma en su DTO
	 * 
	 * @author Pablo Gavilan
	 * @param p
	 *            Entidad persona a transformar en entidad
	 * @return Objeto de tipo persona
	 * @version 1.0
	 * @since 1.0
	 */
	private PersonaDTO transforma(PersonaEntity p) {
		PersonaDTO pe = new PersonaDTO();
		pe.setApellido(p.getApellido());
		pe.setClave(p.getClave());
		pe.setCorreo(p.getCorreo());
		pe.setCumpleano(p.getCumpleano());
		pe.setDireccion(p.getDireccion());
		pe.setGenero(p.getGenero());
		pe.setId(p.getId());
		pe.setNombre(p.getNombre());
		pe.setPosicion(p.getPosicion());
		pe.setRut(p.getRut());
		pe.setTelefono(p.getTelefono());
		return pe;
	}
}
