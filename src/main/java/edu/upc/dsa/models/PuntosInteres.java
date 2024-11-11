package edu.upc.dsa.models;
import java.util.List;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.util.RandomUtils;

public class PuntosInteres {
    private  String id;
    private String type;
    private int x_coordenada;
    private int y_coordenada;
    private List<Usuario> usuarios;

    public PuntosInteres() {
        this.id = RandomUtils.getId();
    }

    public PuntosInteres(String type, int x_coordenada, int y_coordenada) {
        this();
        this.type = type;
        this.x_coordenada = x_coordenada;
        this.y_coordenada = y_coordenada;
    }

    public PuntosInteres(String id, String type, int x_coordenada, int y_coordenada) {
        this(type, x_coordenada, y_coordenada);
        if (id != null) this.id = id;
    }


    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void addUsuario(Usuario u) {
        this.usuarios.add(u);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX_coordenada() {
        return x_coordenada;
    }

    public void setX_coordenada(int x_coordenada) {
        this.x_coordenada = x_coordenada;
    }

    public int getY_coordenada() {
        return y_coordenada;
    }

    public void setY_coordenada(int y_coordenada) {
        this.y_coordenada = y_coordenada;
    }
}