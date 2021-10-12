/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Empresa;
import DTO.Solicitud;
import java.util.ArrayList;
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
public class EmpresaJpaControllerTest {

    EntityManagerFactory emf = Conexion.getConexion().getBd();

    public EmpresaJpaControllerTest() {
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
     * Test of findEmpresa method, of class EmpresaJpaController.
     */
//    @Test
//    public void testFindEmpresa() {
//        System.out.println("findEmpresa");
//        Integer id = 8;
//        EmpresaJpaController instance = new EmpresaJpaController(emf);
//        Empresa expResult = new Empresa(8, "666666", "INGENIEROSS", "ANTIOQUIA", "Medellin", "AV", "3110000000", "3110000000", "ingenieros-soporte@ingenierios.com", "Leonardo", "3112235065", "leonardo@ingenieros.com", new Date());
//        List<Solicitud> lista = new ArrayList<Solicitud>();
//        expResult.setSolicitudList(lista);
//        Empresa result = instance.findEmpresa(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        if (result != expResult) {
//            fail("El test de busqueda de empresa por id ha fallado");
//        }
//    }

    /**
     * Test of getEmpresaCount method, of class EmpresaJpaController.
     */
    @Test
    public void testGetEmpresaCount() {
        System.out.println("getEmpresaCount");
        EmpresaJpaController instance = new EmpresaJpaController(emf);
        int expResult = 3;
        int result = instance.getEmpresaCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (result != expResult) {
            fail("El test de conteo de empresa a fallado");
        }
    }

}
