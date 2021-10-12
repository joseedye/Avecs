/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Usuario;
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
public class UsuarioJpaControllerTest {

    EntityManagerFactory emf = Conexion.getConexion().getBd();

    public UsuarioJpaControllerTest() {
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
     * Test of findUsuario method, of class UsuarioJpaController.
     */
    @Test
    public void testFindUsuario_Integer() {
        System.out.println("findUsuario");
        Integer id = 1;
        String user = "angelleonardovian@ufps.edu.co";
        UsuarioJpaController instance = new UsuarioJpaController(emf);
        Usuario expResult = instance.findUsuario(user);
        Usuario result = instance.findUsuario(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("El test de busqueda por usuario ha fallado");
        }
    }

    /**
     * Test of findUsuario method, of class UsuarioJpaController.
     */
    @Test
    public void testFindUsuario_String() {
        System.out.println("findUsuario");
        Integer user = 1;
        Usuario usuario = new Usuario(1, "angelleonardovian@ufps.edu.co", "12345", new Date(), true);
        UsuarioJpaController instance = new UsuarioJpaController(emf);
        Usuario expResult = instance.findUsuario(usuario.getIdUsuario());
        Usuario result = instance.findUsuario(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("El test de busqueda por id ha fallado");
        }
    }

    /**
     * Test of autenticacion method, of class UsuarioJpaController.
     */
    @Test
    public void testAutenticacion() {
        System.out.println("autenticacion");
        Usuario usuario = new Usuario(1, "angelleonardovian@ufps.edu.co", "12345", new Date(), true);//parametro in
        UsuarioJpaController instance = new UsuarioJpaController(emf);
        Usuario expResult = instance.findUsuario(usuario.getIdUsuario());
        Usuario result = instance.autenticacion(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("El test de autenticaccion ha fallado");
        }
    }

    /**
     * Test of autenticacionGoogle method, of class UsuarioJpaController.
     */
    @Test
    public void testAutenticacionGoogle() {
        System.out.println("autenticacionGoogle");
        Usuario usuario = new Usuario(1, "angelleonardovian@ufps.edu.co", "1", new Date(), true);;
        UsuarioJpaController instance = new UsuarioJpaController(emf);
        Usuario expResult = instance.findUsuario(usuario.getIdUsuario());
        Usuario result = instance.autenticacionGoogle(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("The test case is a prototype.");
        }
    }

}
