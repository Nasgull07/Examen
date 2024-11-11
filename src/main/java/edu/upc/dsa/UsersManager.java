package edu.upc.dsa;
import edu.upc.dsa.models.Usuario;
import java.util.List;
import edu.upc.dsa.models.PuntosInteres;

public interface UsersManager {
    public void addUser(String id, String nombre, String apellidos, String correo, String fecha_nacimiento);
    public Usuario addUser(String nombre, String apellidos, String correo, String fecha_nacimiento);
    public void addUser(Usuario u);
    public Usuario getUser(String id);
    public List<Usuario> ListarOrdenAlfabetico();
    public PuntosInteres añadirPuntoInteres(String type, int x_coordenada, int y_coordenada);
    public Usuario RegistrarPuntoInteres(String id, int x, int y);
    public List<PuntosInteres> ConsultarPuntoInteres(String id);
    public List<Usuario> ConsultarUsuariosPuntoInteres(Integer x, Integer y);
    public List<PuntosInteres> ListarTipoPuntoInteres(String type);

    public void clear();
    public int size();
}
