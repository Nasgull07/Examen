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


@Path("/puntosinteres")
@Api(value = "/puntosinteres", description = "Endpoint to PuntosInteres Service")
public class PuntosInteresService {
    private UsersManager um;

    public PuntosInteresService() {
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
    @ApiOperation(value = "consultar puntos interes usuario", notes = "a partir de cordenadas")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ConsultarPuntosInteres(@PathParam("x_coordenada") Integer x, @PathParam("y_coordenada") Integer y) {

        List<Usuario> users = this.um.ConsultarUsuariosPuntoInteres(x, y);

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }


    @PUT
    @ApiOperation(value = "añadir puntoInteres", notes = "nada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Punto not found")
    })
    @Path("/")
    public Response AñadirPuntoInteres( String type ,int x, int y) {

        PuntosInteres pi = this.um.añadirPuntoInteres(type, x, y);

        if (pi == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "consultar puntos interes ", notes = "a partir de type")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntosInteres.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarPuntosInteres(@PathParam("type") String type) {

        List<PuntosInteres> puntos = this.um.ListarTipoPuntoInteres(type);

        GenericEntity<List<PuntosInteres>> entity = new GenericEntity<List<PuntosInteres>>(puntos) {};
        return Response.status(201).entity(entity).build()  ;

    }






}
