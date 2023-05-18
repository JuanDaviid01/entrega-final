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
                if (temp.getData().getId().equals(kid.getId())) {
                    throw new ListSEException("Ya existe un niño");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getId().equals(kid.getId())) {
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
            Node temp = head;
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
            while (temp != null) {
                invertListSE.addToStart(temp.getData());
                temp = temp.getNext();
            }
            head = invertListSE.getHead();
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
            ListSE listCopboys = new ListSE();
            ListSE listCopFinal = new ListSE();
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    listCopGirls.add(temp.getData());
                } else {
                    listCopboys.add(temp.getData());
                }
                temp = temp.getNext();
            }//while lista copia
            Node tempB = listCopboys.getHead();
            Node tempG = listCopGirls.getHead();
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

    public void orderByNameAtTheEnd(String letter) throws ListSEException {
        ListSE orderListSE = new ListSE();
        if (head != null) {
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getName().startsWith(letter)) {
                    orderListSE.add(temp.getData());
                } else {
                    orderListSE.addToStart(temp.getData());
                }
                temp = temp.getNext();
            }
            head = orderListSE.getHead();
        }
    }// fin ordenar por la inicial del nombre

    public void removeKidsByAge(Byte ageI) {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                if (head.getData().getAge() == ageI) {
                    if (head.getNext() != null) {
                        head = head.getNext();
                        size--;
                    } else {
                        head = null;
                        size--;
                    }
                } else {
                    if (temp.getNext().getData().getAge() == ageI) {
                        temp.setNext(temp.getNext().getNext());
                        size--;
                    } else {
                        temp = temp.getNext();
                    }
                }
            }
        }
    }//fin eliminar por ID

    /*
    -si hay datos
        -si la cabeza es el niño que estoy buscando
            -no podemos avanzar las posiciones
        -si no
            -llamamos a un ayudante para recorrer la lista
            -creamos una copia de la lista
            -recorremosla lista hasta el ultimo
                -si el niño en el que estamos no es el que buscamos
                    -vamos contando el numero de posiciones
                    - agragamos a los niños en la lista copia
            -cuando encontremos
                -lo copiamos
                -si la cantidad de niños contados es mayor que la posicion que nos dieron
                    -  lo añadimos por posicion
                        -con la cantidad de niños que contamos menos la posicion que nos dieron
                -si no
                    -no se pueden avanzar las posiciones
            -al resto de los niños los agregamos al final de la lista copia
     */
    public void advanceXPos(int pos, String code) throws ListSEException {
        if (head != null) {
            Node temp = head;
            ListSE listSECop = new ListSE();
            if (head.getData().getId().equals(code) || size < pos) {
                throw new ListSEException("no puede avanzar las posiciones");
            } else {
                int positions = 1;
                while (temp.getNext() != null) {
                    if (!temp.getData().getId().equals(code)) {
                        listSECop.add(temp.getData());
                        temp = temp.getNext();
                        positions++;
                    } else {
                        Kid kidCop = temp.getData();
                        if (positions < pos) {
                            listSECop.addxPos(kidCop, positions - pos);
                        } else {
                            throw new ListSEException("no puede avanzar las posiciones");
                        }
                        temp = temp.getNext();
                    }
                }
            }
            head = listSECop.getHead();
        }//fin if headNull
    }//fin avanzar posiciones

    public void loseXPos(int pos, String code) throws ListSEException {
        Node temp = this.head;
        int posList = 1;
        ListSE listcop = new ListSE();
        if (head != null) {
            while (temp != null) {
                if (!temp.getData().getId().equalsIgnoreCase(code)) {
                    listcop.add(temp.getData());
                    temp = temp.getNext();
                    posList = posList + 1;
                } else {
                    listcop.addToStart(temp.getData());
                    Kid kidCop = listcop.getHead().getData();
                    listcop.head = listcop.getHead().getNext();
                    int posFinal = posList + pos;
                    listcop.addxPos(kidCop, posFinal);
                    temp = temp.getNext();
                }//fin else
            }//fin while
        }//fin if headNull
        head = listcop.getHead();
    }//fin perder posiciones
}//fin listSE
