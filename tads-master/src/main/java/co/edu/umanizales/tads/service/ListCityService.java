package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.CityDTO;
import co.edu.umanizales.tads.model.City;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ListCityService {
    private ListSEService listSEService = new ListSEService();
    private List<City> cities;
    private List<City> citiesComplete;

    public ListCityService() {
        cities = listSEService.getCitiesOfKids();
        citiesComplete = new ArrayList<>();
    }

    public List<City> getListFinalCities() {
        for (City city : cities) {
            if (!citiesComplete.contains(city)) {
                citiesComplete.add(city);
            }
        }
        return citiesComplete;
    }

    public City getCityById(String id) {
        for (City city : citiesComplete) {
            if (city.getIdCiudad().equalsIgnoreCase(id)) {
                return city;
            }
        }
        return null;
    }

    //metodo para buscar un id en la lista de cities y contar cuantas veces esta
    public int getCitiesQuantityById(String id) {
        int cont = 0;
        if (cities != null) {
            for (City city : cities) {
                if (city.getIdCiudad().equalsIgnoreCase(id)) {
                    cont = cont + 1;
                }
            }
        }
        return cont;
    }//fin metodo getCitiesQuantity


    //metodo para listar las ciudades existentes y la cantidad de niños que tienen....
    public List<CityDTO> getCitiesFinal() {
        List<CityDTO> citiesDTO = new ArrayList<>();
        for (City city : citiesComplete) {
            CityDTO cityDTO = new CityDTO(new City(city.getIdCiudad(), city.getNombre()), getCitiesQuantityById(city.getIdCiudad()));
            citiesDTO.add(cityDTO);
        }
        return citiesDTO;
    }
}//fin ListCityService                               casi me mato haciendo este jejeje








/*
metodo no funcional....

    public List<CityDTO> getKidsOfCities() {
        List<CityDTO> cityDTOList = new ArrayList<>();
            CityDTO quantityCityDTO = new CityDTO(new City(), 0);
            for (City city1 : citiesComplete) {
                int quant = listSEService.getCitiesQuantity(city1.getIdCiudad());
                quantityCityDTO.setQuantity(quant);
                if (quant > 0) {
                    cityDTOList.add(quantityCityDTO);
            }
        }
        return cityDTOList;
    }

 */
