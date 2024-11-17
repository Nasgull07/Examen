package edu.upc.dsa.models;
import edu.upc.dsa.models.PuntosInteres;
import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
    private String id;
    private String nombre;
    private String apellidos;
    private String correo;
    private String fecha_nacimiento;
    private List<PuntosInteres> puntosInteres; // types: DOOR, WALL, BRIDGE, POTION, SWORD, COIN, GRASS, TREE

    public Usuario() {
        this.setId(RandomUtils.getId());
    }
    public Usuario(String nombre, String apellidos , String correo, String fecha_nacimiento) {
        this(null, nombre, apellidos, correo, fecha_nacimiento);
    }

    public Usuario(String id, String nombre, String apellidos, String correo, String fecha_nacimiento) {
        this();
        if (id != null) this.setId(id);
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setCorreo(correo);
        this.setFecha_nacimiento(fecha_nacimiento);
    }

    public List<PuntosInteres> getPuntosInteres() {
        if (this.puntosInteres == null) {
            this.puntosInteres = new LinkedList<>();  // Inicializa la lista si es null
        }
        return puntosInteres;
    }

    public void setPuntosInteres(List<PuntosInteres> puntosInteres) {
        this.puntosInteres = puntosInteres;
    }

    public PuntosInteres getPuntoInteres(int index) {
        if (index >= 0 && index < puntosInteres.size()) {
            return puntosInteres.get(index);
        }
        return null; // o lanza una excepción si prefieres
    }

    public void setPuntoInteres(PuntosInteres puntoInteres) {
        if (this.puntosInteres == null) {
            this.puntosInteres = new ArrayList<>();
        }
        this.puntosInteres.add(puntoInteres);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
