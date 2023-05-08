package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Pet {
    private String id;
    private String name;
    private byte age;
    private  char gender;
    private String breed;
    private Location location;

}//fin pet
