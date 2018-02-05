package com.example.user.app13_consumoserviciosw02;

/**
 * Created by user on 1/2/2018.
 */

public class Contact {

    int cod;
    String id;
    String nombre;
    String email;
    String direccion;
    String genero;


    public Contact()
    {

    }

    public Contact (String id, String nombre, String email, String direccion, String genero)
    {

        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.genero = genero;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
