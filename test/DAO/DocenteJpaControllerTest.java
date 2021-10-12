/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Docente;
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
public class DocenteJpaControllerTest {

    EntityManagerFactory emf = Conexion.getConexion().getBd();

    public DocenteJpaControllerTest() {
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
     * Test of findDocente method, of class DocenteJpaController.
     */
    @Test
    public void testFindDocente() {
        System.out.println("findDocente");
        Integer id = 1;
        DocenteJpaController instance = new DocenteJpaController(emf);
        Docente expResult = new Docente(1, 1112020);
        Docente result = instance.findDocente(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("El test de busqueda de docente por id ha fallado");
        }
    }

    /**
     * Test of getDocenteCount method, of class DocenteJpaController.
     */
    @Test
    public void testGetDocenteCount() {
        System.out.println("getDocenteCount");
        DocenteJpaController instance = new DocenteJpaController(emf);
        int expResult = 1;
        int result = instance.getDocenteCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (result != expResult) {
            fail("El test de conteo de docente ha fallado");
        }
    }


}
