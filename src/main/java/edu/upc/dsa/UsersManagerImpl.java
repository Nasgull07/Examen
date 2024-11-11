package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.PuntosInteres;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class UsersManagerImpl implements  UsersManager {
    private static UsersManager instance;
    protected List<Usuario> usuarios;
    protected List<PuntosInteres> puntosInteres;
    final static Logger logger = Logger.getLogger(UsersManagerImpl.class);

    private UsersManagerImpl() {
        this.usuarios = new LinkedList<>();
        this.puntosInteres = new LinkedList<>();
    }

    public static UsersManager getInstance() {
        if (instance==null) instance = new UsersManagerImpl();
        return instance;
    }


    public void addUser(String id, String nombre, String apellidos, String correo, String fecha_nacimiento) {
        Usuario u = new Usuario(id, nombre, apellidos, correo, fecha_nacimiento);
        this.usuarios.add(u);
        logger.info("User added: " + u);
    }

    public Usuario addUser(String nombre, String apellidos, String correo, String fecha_nacimiento) {
        Usuario u = new Usuario(nombre, apellidos, correo, fecha_nacimiento);
        this.usuarios.add(u);
        logger.info("User added: " + u);
        return u;

    }

    public void addUser(Usuario u) {
        this.usuarios.add(u);
        logger.info("User added: " + u);
    }

    public Usuario getUser(String id) {
        for (Usuario u : this.usuarios) {
            if (u.getId().equals(id)) {
                logger.info("User found: " + u);
                return u;
            }
        }
        logger.warn("User not found: " + id);
        return null;
    }

    public List<Usuario> ListarOrdenAlfabetico() {
        this.usuarios.sort((u1, u2) -> {
            int nameComparison = u1.getNombre().compareToIgnoreCase(u2.getNombre());
            if (nameComparison != 0) {
                return nameComparison;
            }
            return u1.getApellidos().compareToIgnoreCase(u2.getApellidos());
        });
        return this.usuarios;
    }


    public PuntosInteres añadirPuntoInteres(String type, int x_coordenada, int y_coordenada) {
        List<String> validTypes = List.of("DOOR", "WALL", "BRIDGE", "POTION", "SWORD", "COIN", "GRASS", "TREE");
        if (!validTypes.contains(type)) {
            logger.warn("Invalid point of interest type: " + type);
            return null;
        }
        PuntosInteres p = new PuntosInteres(type, x_coordenada, y_coordenada);
        this.puntosInteres.add(p);
        logger.info("Point of interest added: " + p);
        return  p;
    }

    public Usuario RegistrarPuntoInteres(String id, int x, int y) {
        Usuario u = this.getUser(id);
        if (u == null) {
            logger.warn("User not found: " + id);
            return null;
        }

        PuntosInteres puntoInteres = null;
        for (PuntosInteres p : this.puntosInteres) {
            if (p.getX_coordenada() == x && p.getY_coordenada() == y) {
                puntoInteres = p;
                break;
            }
        }

        if (puntoInteres == null) {
            logger.warn("Point of interest not found at coordinates: (" + x + ", " + y + ")");
            return null;
        }

        u.setPuntoInteres(puntoInteres);
        puntoInteres.addUsuario(u);
        logger.info("Point of interest registered for user: " + id + " at coordinates: (" + x + ", " + y + ")");
        return u;
    }

    public List<PuntosInteres> ConsultarPuntoInteres(String id) {
        Usuario u = this.getUser(id);
        if (u != null) {
            return u.getPuntosInteres();
        } else {
            logger.warn("User not found: " + id);
            return new LinkedList<>();
        }
    }

    public List<Usuario> ConsultarUsuariosPuntoInteres(Integer x, Integer y) {
        List<Usuario> result = new LinkedList<>();
        boolean puntoInteresEncontrado = false;
        for (Usuario u : this.usuarios) {
            for (PuntosInteres p : u.getPuntosInteres()) {
                if (p.getX_coordenada() == x && p.getY_coordenada() == y) {
                    result.add(u);
                    puntoInteresEncontrado = true;
                    break;
                }
            }
        }
        if (!puntoInteresEncontrado) {
            logger.warn("Point of interest not found at coordinates: (" + x + ", " + y + ")");
        }
        return result;
    }

    public List<PuntosInteres> ListarTipoPuntoInteres(String type) {
        List<String> validTypes = List.of("DOOR", "WALL", "BRIDGE", "POTION", "SWORD", "COIN", "GRASS", "TREE");
        if (!validTypes.contains(type)) {
            logger.warn("Invalid point of interest type: " + type);
            return new LinkedList<>();
        }
        List<PuntosInteres> result = new LinkedList<>();
        for (PuntosInteres p : this.puntosInteres) {
            if (p.getType().equals(type)) {
                result.add(p);
            }
        }
        return result;
    }

    public void clear() {
        this.usuarios.clear();
        this.puntosInteres.clear();
        logger.info("All users and points of interest cleared");
    }

    public int size() {
        return this.usuarios.size();
    }

}
