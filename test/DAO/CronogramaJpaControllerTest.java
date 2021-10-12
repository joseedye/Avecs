/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Cronograma;
import java.util.Date;
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
public class CronogramaJpaControllerTest {

    EntityManagerFactory emf = Conexion.getConexion().getBd();

    public CronogramaJpaControllerTest() {
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
     * Test of findCronograma method, of class CronogramaJpaController.
     */
    @Test
    public void testFindCronograma() {
        System.out.println("findCronograma");
        Integer id = 1;
        CronogramaJpaController instance = new CronogramaJpaController(emf);
        Cronograma expResult = new Cronograma(1, "actividad 1", "essta es la primera actividad", " no hay observaciones", new Date(2020, 06, 10), new Date(2020, 06, 11), true);
        Cronograma result = instance.findCronograma(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("The buscar cronograma por id ha fallado.");
        }
    }

    /**
     * Test of getCronogramaCount method, of class CronogramaJpaController.
     */
    @Test
    public void testGetCronogramaCount() {
        System.out.println("getCronogramaCount");
        CronogramaJpaController instance = new CronogramaJpaController(emf);
        int expResult = 8;
        int result = instance.getCronogramaCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (result != expResult) {
            fail("The contar cronograma por id ha fallado.");
        }
    }

}
