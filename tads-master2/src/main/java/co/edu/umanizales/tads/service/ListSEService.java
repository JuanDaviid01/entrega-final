package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.exception.ListSEException;
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

    }
    public void invert() {
        kids.invert();
    }

    public float getProm() {
        return kids.promAgeKids();
    }

    public void orderByNameAtTheEnd(String code) throws ListSEException {
        kids.orderByNameAtTheEnd(code);
    }

    public void orderBoysToStart() throws ListSEException {
        kids.orderBoysToStart();
    }

}
