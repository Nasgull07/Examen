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
    @ApiOperation(value = "consultar usuarios que visitaron un punto de interes", notes = "a partir de coordenadas")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer = "List"),
    })
    @Path("/consultarUsuariosDePuntos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ConsultarUsuariosVisitaronPuntoInteres(@QueryParam("x") Integer x, @QueryParam("y") Integer y) {
        List<Usuario> users = this.um.ConsultarUsuariosPuntoInteres(x, y);

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(users) {};
        return Response.status(201).entity(entity).build();
    }


    @GET
    @ApiOperation(value = "consultar puntos interes de un usuario", notes = "a partir de id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntosInteres.class, responseContainer = "List"),
    })
    @Path("/consultarPuntosdeUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ConsultarPuntoInteresdeUsuario(@QueryParam("id") String id) {
        List<PuntosInteres> puntos = this.um.ConsultarPuntosDeUsuario(id);

        GenericEntity<List<PuntosInteres>> entity = new GenericEntity<List<PuntosInteres>>(puntos) {};
        return Response.status(201).entity(entity).build();
    }



    @PUT
    @ApiOperation(value = "añadir puntoInteres", notes = "nada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Punto not found")
    })
    @Path("/añadirPuntosInteres")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AñadirPuntoInteres(PuntosInteres puntoInteres) {
        PuntosInteres pi = this.um.añadirPuntoInteres(puntoInteres.getType(), puntoInteres.getX_coordenada(), puntoInteres.getY_coordenada());

        if (pi == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "consultar puntos interes", notes = "a partir de type")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntosInteres.class, responseContainer = "List"),
    })
    @Path("/listarPuntos/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarPuntosInteres(@PathParam("type") String type) {
        List<PuntosInteres> puntos = this.um.ListarTipoPuntoInteres(type);

        GenericEntity<List<PuntosInteres>> entity = new GenericEntity<List<PuntosInteres>>(puntos) {};
        return Response.status(201).entity(entity).build();
    }






}
