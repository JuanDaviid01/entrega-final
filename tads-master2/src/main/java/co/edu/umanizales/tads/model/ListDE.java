package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;
    int size;

    public void add(Pet pet) throws ListDEException {
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                if (temp.getData().getId().equals(pet.getId())) {
                    throw new ListDEException("Ya existe un niño");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getId().equals(pet.getId())) {
                throw new ListDEException("Ya existe un niño");
            }
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        } else {
            head = new NodeDE(pet);
        }
        size++;
    }//fin añadir niño

    public void addToStart(Pet pet) {
        if (head != null) {
            NodeDE newNode = new NodeDE(pet);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else {
            head = new NodeDE(pet);
        }
        size++;
    }//fin añadir al principio

    public void addxPos(Pet pet, int pos) {
        if (head != null) {
            NodeDE temp = head;
            int cont = 1;
            while (cont < pos - 1) {
                temp = temp.getNext();
                cont = cont + 1;
            }
            NodeDE newNode = new NodeDE(pet);
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        } else {
            head = new NodeDE(pet);
        }
        size++;
    }//fin añadir por posicion

    public void removeById(String id) {
        if (head != null) {
            NodeDE temp = head;
            if (head.getData().getId().equals(id)) {
                head = head.getNext();
            } else {
                while (!temp.getNext().getData().getId().equals(id)) {
                    temp = temp.getNext();
                }
                temp.setNext(temp.getNext().getNext());
            }
        }
        size--;
    }//fin eliminar por ID

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

    public void invertExtremes() {
        if (head != null && this.head.getNext() != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            Pet headCopy = this.getHead().getData();
            head.setData(temp.getData());
            temp.setData(headCopy);
        }
    }//fin invertir extremos

    public void invert() {
        ListDE invertListDE = new ListDE();
        if (head != null) {
            NodeDE temp = head;
            while (temp.getData() != null) {
                invertListDE.addToStart(temp.getData());
                temp.getNext();
            }
        }
    }// fin invertir la lista

    public void orderBoysToStart() throws ListDEException {
        if (head != null) {
            ListDE listCp = new ListDE();
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listCp.addToStart(temp.getData());
                } else {
                    listCp.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }//fin ordenar primero los niños

    public void orderBoysAndGirls() throws ListDEException {
        if (head != null) {
            ListDE listCopGirls = new ListDE();
            ListDE listCopboys = new ListDE();
            ListDE listCopFinal = new ListDE();
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    listCopGirls.add(temp.getData());
                } else {
                    listCopboys.add(temp.getData());
                }
                temp = temp.getNext();
            }//while lista copia
            NodeDE tempB = listCopboys.getHead();
            NodeDE tempG = listCopGirls.getHead();
            while (tempB != null || tempG != null) {
                if (tempB != null) {
                    listCopFinal.add(tempB.getData());
                    tempB = tempB.getNext();
                }
                if (tempG != null) {
                    listCopFinal.add(tempG.getData());
                    tempG = tempG.getNext();
                }
            }
            head = listCopFinal.getHead();
        }//if null
    }//fin intercalar niño niña

}//fin listDE
