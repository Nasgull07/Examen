package edu.upc.dsa;

import edu.upc.dsa.models.PuntosInteres;
import edu.upc.dsa.models.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UsersManagerTest {
    UsersManager um;
    

    @Before
    public void setUp() {
        this.um = UsersManagerImpl.getInstance();
        this.um.addUser("11", "Juan", "Fernandez", "e-mail", "01/01/1990");
        this.um.addUser("12", "Pepe", "Garcia", "e-mail", "01/01/1990");
        this.um.addUser("Maria", "Lopez", "e-mail", "01/01/1990");
        this.um.añadirPuntoInteres("DOOR", 1, 2);
        this.um.añadirPuntoInteres("WALL", 3, 4);
        
    }

    @After
    public void tearDown() {
        // És un Singleton
        this.um.clear();
    }

    @Test
    public void addUserTest() {
        Assert.assertEquals(3, um.size());

        this.um.addUser("Paco", "Martinez", "e-mail", "01/01/1990");

        Assert.assertEquals(4, um.size());

    }

    @Test
    public void getUserTest() throws Exception {
        Assert.assertEquals(3, um.size());

        Usuario u = this.um.getUser("11");
        Assert.assertEquals("Juan", u.getNombre());
        Assert.assertEquals("Fernandez", u.getApellidos());
        Assert.assertEquals("e-mail", u.getCorreo());
        Assert.assertEquals("01/01/1990", u.getFecha_nacimiento());


        u = this.um.getUser("12");
        Assert.assertEquals("Pepe", u.getNombre());
        Assert.assertEquals("Garcia", u.getApellidos());
        Assert.assertEquals("e-mail", u.getCorreo());
        Assert.assertEquals("01/01/1990", u.getFecha_nacimiento());

    }

    @Test
    public void ListUsuarioAlfabetic() {
        Assert.assertEquals(3, um.size());
        List<Usuario> usuarios = um.ListarOrdenAlfabetico();

        Usuario u = usuarios.get(0);
        Assert.assertEquals("Juan", u.getNombre());
        Assert.assertEquals("Fernandez", u.getApellidos());

        u = usuarios.get(1);
        Assert.assertEquals("Maria", u.getNombre());
        Assert.assertEquals("Lopez", u.getApellidos());

        u = usuarios.get(2);
        Assert.assertEquals("Pepe", u.getNombre());
        Assert.assertEquals("Garcia", u.getApellidos());

        Assert.assertEquals(3, um.size());
    }

    @Test
    public void addPuntoInteresTest() {

        this.um.añadirPuntoInteres("DOOR", 1, 5);
        this.um.añadirPuntoInteres("WALL", 9, 4);
        this.um.añadirPuntoInteres("incorrecto", 5, 6);
        Assert.assertEquals(4, um.sizePuntosInteres());
    }

    
    @Test
    public void ListarTipoPuntoInteresTest() {
        this.um.añadirPuntoInteres("DOOR", 11, 2);
        this.um.añadirPuntoInteres("WINDOW", 32, 4);
        this.um.añadirPuntoInteres("DOOR", 5, 61);

        List<PuntosInteres> doors = this.um.ListarTipoPuntoInteres("DOOR");
        Assert.assertEquals(3, doors.size());


    }

    @Test
    public void RegistrarPuntoInteresTest() {
        this.um.RegistrarPuntoInteres("11", 1, 2);
        this.um.RegistrarPuntoInteres("12", 3, 4);
        this.um.RegistrarPuntoInteres("12", 5, 6); //punto inexistente
        this.um.RegistrarPuntoInteres("Maria", 7, 8); //tipo invalido de id

        List<PuntosInteres> puntos = this.um.ConsultarPuntosDeUsuario("11");
        Assert.assertEquals(1, puntos.size());

        puntos = this.um.ConsultarPuntosDeUsuario("12");
        Assert.assertEquals(1, puntos.size());

        puntos = this.um.ConsultarPuntosDeUsuario("Maria");
        Assert.assertEquals(0, puntos.size());

    }

    @Test
    public void ConsultarUsuariosPuntoInteresTest() {
        this.um.RegistrarPuntoInteres("11", 1, 2);
        this.um.RegistrarPuntoInteres("12", 3, 4);
        this.um.RegistrarPuntoInteres("12", 1, 2);
        this.um.RegistrarPuntoInteres("11", 7, 8);


        List<Usuario> users = this.um.ConsultarUsuariosPuntoInteres(1, 2);
        Assert.assertEquals(2, users.size());

        users = this.um.ConsultarUsuariosPuntoInteres(3, 4);
        Assert.assertEquals(1, users.size());

        users = this.um.ConsultarUsuariosPuntoInteres(7, 8);
        Assert.assertEquals(0, users.size());

    }

    @Test
    public void ConsultarPuntosUsuarioTest() {
        this.um.RegistrarPuntoInteres("11", 1, 2);
        this.um.RegistrarPuntoInteres("12", 3, 4);
        this.um.RegistrarPuntoInteres("11", 5, 6);
        this.um.RegistrarPuntoInteres("12", 1, 2);

        List<PuntosInteres> puntos = this.um.ConsultarPuntosDeUsuario("11");
        Assert.assertEquals(1, puntos.size());

        puntos = this.um.ConsultarPuntosDeUsuario("12");
        Assert.assertEquals(2, puntos.size());


    }



}
