package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListDEException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDECircular {
    private NodeDE head;
    int size;

    public List<Pet> getPets() {
        List<Pet> pets = new ArrayList<>();
        NodeDE temp = head;
        if (head != null) {
            while (temp.getNext() != head) {
                pets.add(temp.getData());
                temp = temp.getNext();
            }
        }
        return pets;
    }//fin listar mascotas circular

    public void add(Pet pet) {
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
            newNode.setPrevious(temp);
            newNode.setNext(head);
            head.setPrevious(newNode);
        } else {
            head = new NodeDE(pet);
            head.setPrevious(head);
            head.setNext(head);
        }
        size++;
    }//fin añadir mascota

    public void addToStart(Pet pet) {
        if (head != null) {
            NodeDE temp = head.getPrevious();
            NodeDE newNode = new NodeDE(pet);
            newNode.setNext(head);
            head.setPrevious(newNode);
            temp.setNext(newNode);
            newNode.setPrevious(temp);
            head = newNode;
        } else {
            head = new NodeDE(pet);
            head.setPrevious(head);
            head.setNext(head);
        }
        size++;
    }//fin añadir al principio

    public Boolean searchById(String id) {
        Boolean finded = false;
        if (head != null) {
            NodeDE temp = head;
            if (!head.getData().getId().equals(id)) { //valida la cabeza
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    if (!temp.getData().getId().equals(id)) { //valida la posicion en la que estamos
                    } else {
                        finded = true;
                    }
                }
            } else {
                finded = true;
            }
        }
        return finded;
    }//fin buscar por id

}
