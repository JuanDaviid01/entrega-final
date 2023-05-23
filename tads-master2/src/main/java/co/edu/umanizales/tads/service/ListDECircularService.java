package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.model.ListDECircular;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDECircularService {
    private ListDECircular pets;

    public ListDECircularService() {
        pets = new ListDECircular();
    }

    public void cleanPet() throws ListDEException {
        pets.takeShower();
    }
}//fin service
