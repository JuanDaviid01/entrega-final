package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class ListSE {
    private Node head;

    public void add(Kid kid) {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
    }

    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
    }

    public void removeById(String id) {
        if (head != null) {
            Node temp = head;
            if (head.getData().getIdentification().equals(id)) {
                head = head.getNext();
            } else {
                while (!temp.getNext().getData().getIdentification().equals(id)) {
                    temp = temp.getNext();
                }
                temp.setNext(temp.getNext().getNext());
            }
        }
    }

    public void addxPos(Kid kid, int pos) {
        if (head != null) {
            Node temp = head;
            int cont = 1;
            while (cont < pos - 1) {
                temp = temp.getNext();
                cont = cont + 1;
            }
            Node newNode = new Node(kid);
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
    }

    public float promAgeKids() {
        float prom = 0;
        if (head != null) {
            Node temp = head;
            float cont = 1;
            float sum = temp.getData().getAge();
            while (temp.getNext() != null) {
                temp = temp.getNext();
                cont = cont + 1;
                sum = sum + temp.getData().getAge();
            }
            prom = sum / cont;
        }//fin if
        return prom;
    }// fin metodo promedio de edad



}//fin kid
