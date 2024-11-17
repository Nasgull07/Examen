package edu.upc.dsa;
import edu.upc.dsa.models.Usuario;
import java.util.List;
import edu.upc.dsa.models.PuntosInteres;

public interface UsersManager {
    public void addUser(String id, String nombre, String apellidos, String correo, String fecha_nacimiento);
    public Usuario addUser(String nombre, String apellidos, String correo, String fecha_nacimiento);
    public void addUser(Usuario u);

    public List<Usuario> ListarOrdenAlfabetico();

    public Usuario getUser(String id);

    public PuntosInteres añadirPuntoInteres(String type, int x_coordenada, int y_coordenada);

    public Usuario RegistrarPuntoInteres(String id, int x, int y);

    public List<PuntosInteres>ConsultarPuntosDeUsuario(String id); //Consulta los puntos de interes de un usuario

    public List<Usuario> ConsultarUsuariosPuntoInteres(int x, int y); //Consulta los usuarios que han visitado un punto de interes

    public List<PuntosInteres> ListarTipoPuntoInteres(String type);

    public void clear();
    public int size();
    public int sizePuntosInteres();
}
