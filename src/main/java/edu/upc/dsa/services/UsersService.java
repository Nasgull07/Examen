package edu.upc.dsa.services;

import edu.upc.dsa.UsersManager;
import edu.upc.dsa.UsersManagerImpl;
import edu.upc.dsa.models.PuntosInteres;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/users")
@Api(value = "/users", description = "Endpoint to Users Service")

public class UsersService {
    private UsersManager um;

    public UsersService() {
        this.um = UsersManagerImpl.getInstance();
        if (um.size()==0) {
            this.um.addUser("11", "Juan", "Fernandez", "juan@email.com", "01/01/1990");
            this.um.addUser("12", "Pepe", "Garcia", "pepe@email.com", "02/02/1992");
            this.um.addUser("13", "Maria", "Lopez", "maria@email.com", "03/03/1995");

            // Añadir puntos de interés
            PuntosInteres punto1 = this.um.añadirPuntoInteres("DOOR", 1, 2);
            PuntosInteres punto2 = this.um.añadirPuntoInteres("WALL", 3, 4);
            PuntosInteres punto3 = this.um.añadirPuntoInteres("PARK", 5, 6);
            PuntosInteres punto4 = this.um.añadirPuntoInteres("LAKE", 7, 8);

            // Asociar algunos puntos de interés con los usuarios
            this.um.RegistrarPuntoInteres("11", 1, 2); // Juan ha visitado "DOOR"
            this.um.RegistrarPuntoInteres("12", 3, 4); // Pepe ha visitado "WALL"
            this.um.RegistrarPuntoInteres("12", 5, 6); // Pepe ha visitado "PARK"
            this.um.RegistrarPuntoInteres("13", 7, 8); // Maria ha visitado "LAKE"

        }


    }

    @GET
    @ApiOperation(value = "listar usuarios orden alfabetico", notes = "nada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })

    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarOrdenAlfabetico() {

        List<Usuario> users = this.um.ListarOrdenAlfabetico();

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }



    @GET
    @ApiOperation(value = "get a User", notes = "a traves del id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        Usuario u = this.um.getUser(id);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }

    @PUT
    @ApiOperation(value = "añadir punto de interes", notes = "que un usuario ha visitado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/añadirPuntoInteres/{id}/{x}/{y}")
    public Response RegistrarPuntoInteres(@PathParam("id") String id,
                                          @PathParam("x") int x,
                                          @PathParam("y") int y) {
        Usuario u = this.um.RegistrarPuntoInteres(id, x, y);

        if (u == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "añadir usuario", notes = "nada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/add")
    public Response AñadirUsuario(Usuario usuario) {

        Usuario u = this.um.addUser(usuario.getNombre(), usuario.getApellidos(), usuario.getCorreo(), usuario.getFecha_nacimiento());

        if (u == null) return Response.status(404).build();

        return Response.status(201).build();
    }

}
