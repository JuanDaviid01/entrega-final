package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class City {
    String idCiudad;
    String nombre;

    public City() {
    }

    public City(String idCiudad, String nombre) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
    }

    public City(City city) {
    }
}
