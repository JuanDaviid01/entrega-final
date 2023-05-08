package co.edu.umanizales.tads.controller.dto;

import lombok.Data;

@Data
public class PetDTO {
    private String id;
    private String name;
    private byte age;
    private char gender;
    private String breed;
    private String codeLocation;
}
