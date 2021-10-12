/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Asignatura;
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
public class AsignaturaJpaControllerTest {

    EntityManagerFactory emf = Conexion.getConexion().getBd();

    public AsignaturaJpaControllerTest() {
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
     * Test of findAsignatura method, of class AsignaturaJpaController.
     */
    @Test
    public void testFindAsignatura() {
        System.out.println("findAsignatura");
        Integer id = 1;
        AsignaturaJpaController instance = new AsignaturaJpaController(emf);
        Asignatura expResult = new Asignatura(1, "Planeación de la Comunicación", 1330605, 6);
        Asignatura result = instance.findAsignatura(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("El test de busqueda de asignatura ha fallado");
        }
    }

    /**
     * Test of getAsignaturaCount method, of class AsignaturaJpaController.
     */
    @Test
    public void testGetAsignaturaCount() {
        System.out.println("getAsignaturaCount");
        AsignaturaJpaController instance = new AsignaturaJpaController(emf);
        int expResult = 2;
        int result = instance.getAsignaturaCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (result!=expResult) {
            fail("El test de busqueda de asignatura ha fallado");
        }
    }

}
