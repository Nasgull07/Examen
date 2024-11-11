package edu.upc.dsa.services;

import edu.upc.dsa.UsersManager;
import edu.upc.dsa.UsersManagerImpl;
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
            this.um.addUser("11", "Juan", "Fernandez", "e-mail", "01/01/1990");
            this.um.addUser("12", "Pepe", "Garcia", "e-mail", "01/01/1990");
            this.um.addUser("Maria", "Lopez", "e-mail", "01/01/1990");
            this.um.añadirPuntoInteres("DOOR", 1, 2);
            this.um.añadirPuntoInteres("WALL", 3, 4);

        }


    }

    @GET
    @ApiOperation(value = "listar usuarios orden alfabetico", notes = "nada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/")
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
    @Path("/")
    public Response RegistrarPuntoInteres(String id, int x, int y) {

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
    @Path("/")
    public Response AñadirUsuario(String nombre, String apellido, String email, String fecha) {

        Usuario u = this.um.addUser(nombre, apellido, email, fecha);

        if (u == null) return Response.status(404).build();

        return Response.status(201).build();
    }

}
