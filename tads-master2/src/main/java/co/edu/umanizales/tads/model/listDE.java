package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class listDE {
    private NodeDE head;
    public void add(Pet pet) {
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            NodeDE newNodeDE = new NodeDE(pet);
            temp.setNext(newNodeDE);
        } else {
            head = new NodeDE(pet);
        }
    }
}//fin listDE
