package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.Data;

@Data
public class ListSE {
    private Node head;
    private int size;

    public void add(Kid kid) throws ListSEException {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                if (temp.getData().getIdentification().equals(kid.getIdentification())) {
                    throw new ListSEException("Ya existe un niño");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getIdentification().equals(kid.getIdentification())) {
                throw new ListSEException("Ya existe un niño");
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
        size++;
    }//fin añadir niño

    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
        size++;
    }//fin añadir al principio

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
        size++;
    }//fin añadir por posicion

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
        size--;
    }//fin eliminar por ID

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
    }//fin invertir extremos

    public void invert() {
        ListSE invertListSE = new ListSE();
        if (head != null) {
            Node temp = head;
            while (temp.getData() != null) {
                invertListSE.addToStart(temp.getData());
                temp.getNext();
            }
        }
    }// fin invertir la lista

    public void orderBoysToStart() throws ListSEException {
        if (head != null) {
            ListSE listCp = new ListSE();
            Node temp = head;
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

    public void orderBoysAndGirls() throws ListSEException {
        if (head != null) {
            ListSE listCopGirls = new ListSE();
            Node temp = head;
            int pos = 1;
            while (temp.getData() != null) {
                if (temp.getData().getGender() == 'F') {
                    listCopGirls.add(temp.getData());
                    temp = temp.getNext();
                }
            }//while lista copia
            while (temp.getData() != null) {
                if (pos % 2 != 0) {

                }
                temp = temp.getNext();
                pos = pos + 1;
            }
        }//if null
    }//fin intercalar niño, niña

    public void removeKidByAge(Byte ageI) {
        if (head != null) {
            int count = 0;
            Node temp = head;
            while (temp.getNext() != null) {
                if (head.getData().getAge() == ageI) {
                    head = head.getNext();
                    count = count + 1;
                } else {
                    while (temp.getNext().getData().getAge() != ageI) {
                        temp = temp.getNext();
                    }//fin while nextAge
                    temp.setNext(temp.getNext().getNext());
                    count = count + 1;
                }//else if
            }//fin while data null
            size = size - count;
        }// if null
    }//eliminar niños por la edad

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
    }// fin promedio de edad

    public void advanceXPos(int pos, String code) {
        if (head != null) {
            Node temp = head;
            int posList = 1;
            if (!head.getData().getIdentification().equals(code)) {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    if (temp.getData().getIdentification().equals(code)) {
                        Node copyKid = temp.getNext();

                    }
                }//fin while
            }  //else la cabeza es el niño buscado
        }//fin if headNull
    }//fin avanzar ciertas posiciones

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

    public void orderByNameAtTheEnd(String letter) throws ListSEException {
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
    }// fin ordenar por la inicial del nombre

    public void getReportKidsByLocationGendersByAge(byte age, ReportKidsLocationGenderDTO report) {
        if (head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getAge() > age) {
                    report.updateQuantity(
                            temp.getData().getLocation().getName(),
                            temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
    }//fin reporte por ciudad por genero

}//fin listSE
