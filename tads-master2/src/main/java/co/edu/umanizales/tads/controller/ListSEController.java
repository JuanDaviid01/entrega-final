package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids() {
        return new ResponseEntity<>(new ResponseDTO(200, listSEService.getKids(), null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert() {
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(200, "Se  invirtio la lista correctamente", null), HttpStatus.OK);
    }

    @GetMapping(path = "/prom")
    public ResponseEntity<ResponseDTO> getPromAges() {
        return new ResponseEntity<>(new ResponseDTO(200, listSEService.getProm(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/invert_extremes")
    public ResponseEntity<ResponseDTO> invertExtremes() {
        listSEService.getKids().invertExtremes();
        return new ResponseEntity<>(new ResponseDTO(200, "se intercambiaron los extremos correctamente", null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO) {
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
        }
        if (!listSEService.getKids().searchById(kidDTO.getId())) {
            listSEService.getKids().add(new Kid(kidDTO.getId(), kidDTO.getName(), kidDTO.getAge(), kidDTO.getGender(), location));
            return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado al petacón", null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(200, "Ese codigo ya esta registrado", null), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/kidsbycities")
    public ResponseEntity<ResponseDTO> getKidsByCities() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            int count = listSEService.getKids().getCountKidsInCitiesByLocationCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsByLocationDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbydepartments")
    public ResponseEntity<ResponseDTO> getKidsByDepartments() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocationsByCodeSize(5)) {
            int count = listSEService.getKids().getCountKidsInDepartmentsByLocationCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsByLocationDTOList, null), HttpStatus.OK);
    }

}
