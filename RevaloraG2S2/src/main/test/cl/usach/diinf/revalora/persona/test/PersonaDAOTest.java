package cl.usach.diinf.revalora.persona.test;

import cl.usach.diinf.revalora.persona.dao.PersonaDAO;
import cl.usach.diinf.revalora.persona.dao.PersonaDAOImpl;
import junit.framework.TestCase;

public class PersonaDAOTest extends TestCase {

	public void test() {
		PersonaDAOImpl dao = null;

		try {
			dao = new PersonaDAO();
			assertNotNull(dao);	
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			dao.obtenerPersonas();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
