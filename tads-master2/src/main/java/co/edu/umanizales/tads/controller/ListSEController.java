package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListSEException;
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

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO) throws ListSEException {
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


    @GetMapping(path = "/invert_extremes")
    public ResponseEntity<ResponseDTO> invertExtremes() {
        listSEService.getKids().invertExtremes();
        return new ResponseEntity<>(new ResponseDTO(200, "se intercambiaron los extremos correctamente", null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert() {
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(200, "Se  invirtio la lista correctamente", null), HttpStatus.OK);
    }

    @GetMapping("/order_boys_to_start")
    public ResponseEntity<ResponseDTO> orderBoysToStart() throws ListSEException {
        listSEService.orderBoysToStart();
        return new ResponseEntity<>(new ResponseDTO(200, "Se  pusieron a los niños adelante", null), HttpStatus.OK);
    }

    @GetMapping("/order_boys_and_girls")
    public ResponseEntity<ResponseDTO> orderBoysAndGirls() throws ListSEException {
        listSEService.orderBoysAndGirls();
        return new ResponseEntity<>(new ResponseDTO(200, "se intercalaron a los niños y a las niñas...", null), HttpStatus.OK);
    }
    @GetMapping(path = "/advance_positions/{pos}/{code}")
    public ResponseEntity<ResponseDTO> advanceXPos(@PathVariable int pos, String code) throws ListSEException {
        listSEService.advanceXPos(pos, code);
        return new ResponseEntity<>(new ResponseDTO(200, "el niño a avanzado posiciones correctamente", null), HttpStatus.OK);
    }

    @GetMapping(path = "/lose_positions/{pos}/{code}")
    public ResponseEntity<ResponseDTO> loseXPos(@PathVariable int pos, String code) throws ListSEException {
        listSEService.loseXPos(pos, code);
        return new ResponseEntity<>(new ResponseDTO(200, "el niño a perdido posiciones correctamente", null), HttpStatus.OK);
    }
    @GetMapping(path = "/remove_kid_by_age/{age}")
    public ResponseEntity<ResponseDTO> removeKidByAge(@PathVariable byte age) {
        listSEService.getKids().removeKidsByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "Se han eliminado a los niños con esa edad", null), HttpStatus.OK);
    }

    @GetMapping(path = "/prom_ages")
    public ResponseEntity<ResponseDTO> getPromAges() {
        return new ResponseEntity<>(new ResponseDTO(200, listSEService.getProm(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/kids_by_cities")
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

    @GetMapping(path = "/kids_by_departments")
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

    @GetMapping("/order_by_name_at_the_end/{code}")
    public ResponseEntity<ResponseDTO> orderByNameAtTheEnd(@PathVariable String code) throws ListSEException {
        listSEService.orderByNameAtTheEnd(code);
        return new ResponseEntity<>(new ResponseDTO(200, "todos los que tengan esa incial estan al final...", null), HttpStatus.OK);
    }

    @GetMapping(path = "/kids_by_location_genders/{age}")
    public ResponseEntity<ResponseDTO> getReportKisLocationGenders(@PathVariable byte age) {
        ReportKidsLocationGenderDTO report =
                new ReportKidsLocationGenderDTO(locationService.getLocationsByCodeSize(8));
        listSEService.getKids()
                .getReportKidsByLocationGendersByAge(age, report);
        return new ResponseEntity<>(new ResponseDTO(
                200, report,
                null), HttpStatus.OK);
    }

}
