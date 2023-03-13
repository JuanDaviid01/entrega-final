package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();
        kids.add(new Kid("123", "Carlos", (byte) 4, new City("1", "Manizales")));
        kids.add(new Kid("789", "Lupita", (byte) 5, new City("1", "Manizales")));
        kids.add(new Kid("9", "Lucia", (byte) 89, new City("1", "Manizales")));
        kids.addToStart(new Kid("967", "Estefania", (byte) 6, new City("2", "Chinchina")));
        kids.addxPos(new Kid("9999", "Mauricio", (byte) 2, new City("1", "Manizales")), 4);
        kids.removeById("9");
    }

    public Node getKids() {
        return kids.getHead();
    }

    public float getProm() {
        return kids.promAgeKids();
    }


    /* creamos un metodo para recorrer la lista simplemento enlazada
paara preguntarle a cada niño cual es su ciudad
y esa ciudad la almacenamos en nuestra lista de ciudades
*/
    public List<City> getCitiesOfKids() {
        List<City> cities = new ArrayList<>();
        if (kids.getHead() != null) {
            Node temp = kids.getHead();
            cities.add(kids.getHead().getData().getCity());
            while (temp.getNext() != null) {
                temp = temp.getNext();
                cities.add(temp.getData().getCity());
            }
        }
        return cities;
    }

}

/*
metodo no funcional...

    //metodo para buscar un id de ciudad en la lista de los niños
    public int getCitiesQuantity(String id) {
        int cont = 0;
        if (kids.getHead() != null) {
            Node temp = kids.getHead();
            if (temp.getData().getCity().getIdCiudad().equalsIgnoreCase(id)) {
                cont = cont + 1;
            }
            while (temp.getNext() != null) {
                temp = temp.getNext();
                if (temp.getData().getCity().getIdCiudad().equalsIgnoreCase(id)) {
                    cont = cont + 1;
                }
            }
        }
        return cont;
    }
    //esta busca en la listSE

 */