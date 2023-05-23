package co.edu.umanizales.tads.controller;


import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDECircularService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/listde_circular")
public class ListDECircularController {
    @Autowired
    private ListDECircularService listDECircularService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(200, listDECircularService.getPets().getPets(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@RequestBody PetDTO petDTO) throws ListDEException {
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
        }
        listDECircularService.getPets().add(new Pet(petDTO.getId(), petDTO.getName(), petDTO.getAge(), petDTO.getGender(), false, location));
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado la mascota", null), HttpStatus.OK);
    }

    @PostMapping(path = "/add_to_start")
    public ResponseEntity<ResponseDTO> addToPetStart(@RequestBody PetDTO petDTO) {
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
        }
        if (!listDECircularService.getPets().searchById(petDTO.getId())) {
            listDECircularService.getPets().addToStart(new Pet(petDTO.getId(), petDTO.getName(), petDTO.getAge(), petDTO.getGender(), false, location));
            return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado a la mascota", null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(200, "Ese codigo ya esta registrado", null), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/clean_pet")
    public ResponseEntity<ResponseDTO> addToPetStart() throws ListDEException {
        listDECircularService.cleanPet();
        return new ResponseEntity<>(new ResponseDTO(200, "se baño a la mascota jejejejejej", null), HttpStatus.OK);

    }
}
