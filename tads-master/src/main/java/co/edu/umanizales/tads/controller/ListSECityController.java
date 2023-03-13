package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.service.ListCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "cities")
public class ListSECityController {
    @Autowired
    private ListCityService listCityService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getCities() {
        return new ResponseEntity<>(new ResponseDTO(200, listCityService.getListFinalCities(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<ResponseDTO> getCityById(@PathVariable String code) {
        return new ResponseEntity<>(new ResponseDTO(200, listCityService.getCityById(code), null), HttpStatus.OK);
    }


    @GetMapping(path = "/reportcities")
    public ResponseEntity<ResponseDTO> getQuantityOfCities(){
        return new ResponseEntity<>(new ResponseDTO(200, listCityService.getCitiesFinal(), null), HttpStatus.OK);
    }


}
