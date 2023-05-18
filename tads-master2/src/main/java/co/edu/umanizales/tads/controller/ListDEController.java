package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.getPets().getPets(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody PetDTO petDTO) throws ListDEException {
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
        }
        if (!listDEService.getPets().searchById(petDTO.getId())) {
            listDEService.getPets().add(new Pet(petDTO.getId(), petDTO.getName(), petDTO.getAge(), petDTO.getGender(), location));

            return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado la mascota", null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(200, "Ese codigo ya esta registrado", null), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/add_to_start")
    public ResponseEntity<ResponseDTO> addToPetStart(@RequestBody PetDTO petDTO)  {
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
        }
        if (!listDEService.getPets().searchById(petDTO.getId())) {
            listDEService.getPets().addToStart(new Pet(petDTO.getId(), petDTO.getName(), petDTO.getAge(), petDTO.getGender(), location));
            return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado a la mascota", null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(200, "Ese codigo ya esta registrado", null), HttpStatus.OK);
        }
    }
    @GetMapping(path = "/invert_extremes")
    public ResponseEntity<ResponseDTO> invertExtremes() {
        listDEService.getPets().invertExtremes();
        return new ResponseEntity<>(new ResponseDTO(200, "se intercambiaron los extremos correctamente", null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert() {
        listDEService.invert();
        return new ResponseEntity<>(new ResponseDTO(200, "Se  invirtio la lista correctamente", null), HttpStatus.OK);
    }

    @GetMapping("/order_male_to_start")
    public ResponseEntity<ResponseDTO> orderBoysToStart() throws ListDEException {
        listDEService.orderMalesToStart();
        return new ResponseEntity<>(new ResponseDTO(200, "Se  pusieron a los machos adelante", null), HttpStatus.OK);
    }

    @GetMapping("/order_males_and_females")
    public ResponseEntity<ResponseDTO> orderBoysAndGirls() throws ListDEException {
        listDEService.orderMalesAndFemales();
        return new ResponseEntity<>(new ResponseDTO(200, "se intercalaron a los machos y a las hembras...", null), HttpStatus.OK);
    }

    @GetMapping(path = "/advance_positions/{pos}/{code}")
    public ResponseEntity<ResponseDTO> advanceXPos(@PathVariable int pos, String code) throws ListDEException {
        listDEService.advanceXPos(pos, code);
        return new ResponseEntity<>(new ResponseDTO(200, "la mascota a avanzado posiciones correctamente", null), HttpStatus.OK);
    }

    @GetMapping(path = "/lose_positions/{pos}/{code}")
    public ResponseEntity<ResponseDTO> loseXPos(@PathVariable int pos, String code) throws ListDEException {
        listDEService.loseXPos(pos, code);
        return new ResponseEntity<>(new ResponseDTO(200, "la mascota a perdido posiciones correctamente", null), HttpStatus.OK);
    }

    @GetMapping(path = "/remove_pet_by_age/{age}")
    public ResponseEntity<ResponseDTO> removePetByAge(@PathVariable byte age) {
        listDEService.getPets().removePetsByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "Se han eliminado a las mascotas con esa edad", null), HttpStatus.OK);
    }

    @GetMapping(path = "/prom_ages")
    public ResponseEntity<ResponseDTO> getPromAges() {
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.getProm(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/pets_by_cities")
    public ResponseEntity<ResponseDTO> getPetsByCities() {
        List<PetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            int count = listDEService.getPets().getCountPetsInCitiesByLocationCode(loc.getCode());
            if (count > 0) {
                petsByLocationDTOList.add(new PetsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, petsByLocationDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/pets_by_departments")
    public ResponseEntity<ResponseDTO> getKidsByDepartments() {
        List<PetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocationsByCodeSize(5)) {
            int count = listDEService.getPets().getCountPetsInDepartmentsByLocationCode(loc.getCode());
            if (count > 0) {
                petsByLocationDTOList.add(new PetsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, petsByLocationDTOList, null), HttpStatus.OK);
    }

    @GetMapping("/order_by_name_at_the_end/{code}")
    public ResponseEntity<ResponseDTO> orderByNameAtTheEnd(@PathVariable String code) throws ListDEException {
        listDEService.orderByNameAtTheEnd(code);
        return new ResponseEntity<>(new ResponseDTO(200, "todos los que tengan esa incial estan al final...", null), HttpStatus.OK);
    }

    @GetMapping("/remove_kamikase/{code}")
    public ResponseEntity<ResponseDTO> removeKamikase(@PathVariable String code) {
        listDEService.removeKamikase(code);
        return new ResponseEntity<>(new ResponseDTO(200, "se elimino la mascota", null), HttpStatus.OK);
    }


}//fin controller
