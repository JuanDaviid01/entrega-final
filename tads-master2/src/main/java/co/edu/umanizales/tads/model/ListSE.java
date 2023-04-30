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
    }//fin añadir

    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
    }//fin añadir al principio

    public Boolean searchById(String id) {
        Boolean finded = false;
        if (head != null) {
            Node temp = head;
            if (!head.getData().getIdentification().equals(id)) { //valida la cabeza
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    if (!temp.getData().getIdentification().equals(id)) { //valida la posicion en la que estamos
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
    }//fin eliminar por ID

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
    }//fin añadir por posicion

    public void invertExtremes() {
        if (head != null && this.head.getNext() != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            Kid headCopy = this.getHead().getData();
            head.setData(temp.getData());
            temp.setData(headCopy);
        }
    }//fin metodo para invertir extremos

    public void invert() {
        ListSE invertListSE = new ListSE();
        if (head != null) {
            Node temp = head;
            while (temp.getData() != null) {
                invertListSE.addToStart(temp.getData());
                temp.getNext();
            }
        }
    }// fin metodo para invertir la lista

    public void orderBoysToStart() {
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
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

    public int getCountKidsInCitiesByLocationCode(String code) {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count = count + 1;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }//fin cantidad de niños por ciudad

    public int getCountKidsInDepartmentsByLocationCode(String code) {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().contains(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }//fin cantidad de niños por departamento

    public ListSE orderByNameAtTheEnd(String letter) {
        ListSE orderListSE = new ListSE();
        if (head != null) {
            Node temp = head;
            while (temp.getData() != null) {
                if (temp.getData().getName().startsWith(letter)) {
                    orderListSE.add(temp.getData());
                } else {
                    orderListSE.addToStart(temp.getData());
                }
            }
        }
        return orderListSE;
    }// fin ordenar por la inicial del nombre

    public void advanceXPos(int pos, String code) {
        if (head != null) {
            Node temp = head;
            int posList = 1;
            if (!head.getData().getIdentification().equals(code)) {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    if(temp.getData().getIdentification().equals(code)){
                        Node copyKid = temp.getNext();

                    }
                }//fin while
            }  //else la cabeza es el niño buscado
        }//fin if headNull
    }//fin avanzar ciertas posiciones

}//fin listSE
