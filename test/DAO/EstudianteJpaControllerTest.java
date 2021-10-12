/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Estudiante;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rozo-
 */
public class EstudianteJpaControllerTest {

    EntityManagerFactory emf = Conexion.getConexion().getBd();

    public EstudianteJpaControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findEstudiante method, of class EstudianteJpaController.
     */
    @Test
    public void testFindEstudiante() {
        System.out.println("findEstudiante");
        Integer id = 1;
        EstudianteJpaController instance = new EstudianteJpaController(emf);
        Estudiante expResult = new Estudiante(1, 1151646, "jose eduardo", "3122663067", "LIBERTY SEGUROS DE VIDA S.A");
        Estudiante result = instance.findEstudiante(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("El test de busqueda de estudiante por id a fallado");
        }
    }

    /**
     * Test of getEstudianteCount method, of class EstudianteJpaController.
     */
    @Test
    public void testGetEstudianteCount() {
        System.out.println("getEstudianteCount");
        EstudianteJpaController instance = new EstudianteJpaController(emf);
        int expResult = 2;
        int result = instance.getEstudianteCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (result != expResult) {
            fail("El test de conteo de estudiante a fallado");
        }
    }

    /**
     * Test of findEstudianteByPersona method, of class EstudianteJpaController.
     */
    @Test
    public void testFindEstudianteByPersona() {
        System.out.println("findEstudianteByPersona");
        int idPersona = 3;
        EstudianteJpaController instance = new EstudianteJpaController(emf);
        Estudiante expResult = new Estudiante(1, 1151646, "jose eduardo", "3122663067", "LIBERTY SEGUROS DE VIDA S.A");
        Estudiante result = instance.findEstudianteByPersona(idPersona);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("El test de busqueda de estudiante por persona a fallado");
        }
    }

}
