package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.model.ListDE;


public class ListDEService {
    private ListDE pets;

    public ListDEService() {
        pets = new ListDE();

    }

    public void invert() {
        pets.invert();
    }

    public float getProm() {
        return pets.promAgePets();
    }

    public void orderByNameAtTheEnd(String code) throws ListDEException {
        pets.orderByNameAtTheEnd(code);
    }

    public void orderMalesToStart() throws ListDEException {
        pets.orderMalesToStart();
    }

    public void orderMalesAndFemales() throws  ListDEException {
        pets.orderMalesAndFemales();
    }

    public void advanceXPos(int pos, String code) throws ListDEException {
        pets.advanceXPos(pos, code);
    }

    public void loseXPos(int pos, String code) throws ListDEException {
        pets.loseXPos(pos, code);
    }
}//fin service
