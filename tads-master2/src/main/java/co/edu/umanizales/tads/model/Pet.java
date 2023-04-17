package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class Pet {
    private String id;
    private String name;
    private byte age;
    private  char gender;
    private String breed;
    private Location location;

}//fin pet
