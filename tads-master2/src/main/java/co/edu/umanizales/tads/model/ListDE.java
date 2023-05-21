package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListDEException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;
    int size;

    public List<Pet> getPets() {
        List<Pet> pets = new ArrayList<>();
        NodeDE temp = head;
        if (head != null) {
            while (temp != null) {
                pets.add(temp.getData());
                temp = temp.getNext();
            }
        }
        return pets;
    }//fin listar mascotas

    public void add(Pet pet)  {
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        } else {
            head = new NodeDE(pet);
        }
        size++;
    }//fin añadir mascota

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
        if (head != null && head.getNext() != null) {
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
            while (temp != null) {
                invertListDE.addToStart(temp.getData());
                temp = temp.getNext();
            }
            head = invertListDE.getHead();
        }
    }// fin invertir la lista

    public void orderMalesToStart() throws ListDEException {
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
            head = listCp.getHead();
        }
    }//fin ordenar primero los machos

    public void orderMalesAndFemales() throws ListDEException {
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
    }//fin intercalar macho hembra
    public float promAgePets() {
        float prom = 0;
        if (head != null) {
            NodeDE temp = head;
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

    public int getCountPetsInCitiesByLocationCode(String code) {
        int count = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count = count + 1;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }//fin cantidad de mascotas por ciudad

    public int getCountPetsInDepartmentsByLocationCode(String code) {
        int count = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().contains(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }//fin cantidad de mascotas por departamento

    public void removeKamikase(String id) {
        if (head != null) {
            NodeDE temp = head;
            if (head.getData().getId().equals(id)) {
                if (head.getNext() != null) {
                    temp.getNext().setPrevious(null);
                    head = head.getNext();
                } else {
                    head = null;
                }
            } else {
                while (temp != null) {
                    if (temp.getData().getId().equals(id)) {
                        if (temp.getNext() != null) {
                            temp.getPrevious().setNext(temp.getNext());
                            temp.getNext().setPrevious(temp.getPrevious());
                        } else {
                            temp.getPrevious().setNext(null);
                            temp.setPrevious(null);
                        }
                    }
                    temp = temp.getNext();
                }
            }
        }
        size--;
    }//fin eliminar kamikase

    /*
metodo para eliminar por edad..
-si hay datos...
     -preguntamos si la cabeza tiene la edad que estoy buscando
     -si tiene mas datos aparte de la cabeza
         - le decimos al siguiente que su previo sea nulo
         - y le decimos que la cabeza es igual al siguiente
-si no tiene mas datos a parte de la cabeza
 -le decimos que la cabeza es nulo
-si no es la cabeza...
     - recorremos la listaDE para buscar la edad
-si no es el ulitmo...
     - le decimos a nuestro ayudante que se mueva hacia el previo
y coja como siguiente al siguiente de la mascota que quiero eliminar
 -luego le decimos al ayudante que se mueva al siguiente de la mascota que queremos eliminar
y le decimos que coja como previo al anterior de la mascota.
     -si es el ulitmo
 -le decimos que el previo del niño en el que estamos es igual a nulo
 -le decimos al previo que su siguiente es nulo...
*/
    public void removePetsByAge(Byte ageI) {
        if (head != null) {
            NodeDE temp = head;
            while (temp != null) {
                if (head.getData().getAge() == ageI) {
                    if (head.getNext() != null) {
                        temp.getNext().setPrevious(null);
                        head = head.getNext();
                        size--;
                    } else {
                        head = null;
                        size--;
                    }
                } else {
                    if (temp.getData().getAge() == ageI) {
                        if (temp.getNext() != null) {
                            temp.getPrevious().setNext(temp.getNext());
                            temp.getNext().setPrevious(temp.getPrevious());
                            size--;
                        } else {
                            temp.getPrevious().setNext(null);
                            temp.setPrevious(null);
                            size--;
                        }
                    }
                }
                temp = temp.getNext();
            }
        }
    }//eliminar por la edad

    public void orderByNameAtTheEnd(String letter) throws ListDEException {
        ListDE orderListDE = new ListDE();
        if (head != null) {
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getName().startsWith(letter)) {
                    orderListDE.add(temp.getData());
                    temp = temp.getNext();
                } else {
                    orderListDE.addToStart(temp.getData());
                    temp = temp.getNext();
                }
            }
            head = orderListDE.getHead();
        }
    }// fin ordenar por la inicial del nombre

    public void advanceXPos(int pos, String code) throws ListDEException {
        NodeDE temp = head;
        int posList = 1;
        ListDE listcop = new ListDE();
        if (head != null) {
            while (temp != null) {
                if (!temp.getData().getId().equals(code)) {
                    listcop.add(temp.getData());
                    temp = temp.getNext();
                    posList = posList + 1;
                } else {
                    listcop.addToStart(temp.getData());
                    Pet petCop = listcop.getHead().getData();
                    listcop.head = listcop.getHead().getNext();
                    int posFinal = posList - pos;
                    listcop.addxPos(petCop, posFinal);
                    temp = temp.getNext();
                }//fin else
            }//fin while
        }//fin if headNull
        head = listcop.getHead();
    }//fin avanzar posiciones

    public void loseXPos(int pos, String code) throws ListDEException {
        NodeDE temp = this.head;
        int posList = 1;
        ListDE listcop = new ListDE();
        if (head != null) {
            while (temp != null) {
                if (!temp.getData().getId().equalsIgnoreCase(code)) {
                    listcop.add(temp.getData());
                    temp = temp.getNext();
                    posList = posList + 1;
                } else {
                    listcop.addToStart(temp.getData());
                    Pet petCop = listcop.getHead().getData();
                    listcop.head = listcop.getHead().getNext();
                    int posFinal = posList + pos;
                    listcop.addxPos(petCop, posFinal);
                    temp = temp.getNext();
                }//fin else
            }//fin while
        }//fin if headNull
        this.head = listcop.getHead();
    }//fin perder posiciones

}//fin listDE