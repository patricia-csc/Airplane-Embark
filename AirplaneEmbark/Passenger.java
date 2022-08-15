package AirplaneEmbark;
import java.util.ArrayList;

/**
 * Clasa abstractă Passenger definește un tip de pasager ce poate
 * îmbarca avionul (familie, grup sau singur) și conține un id
 * (exemplu: s3, f13, g2), o listă cu persoane, tipul de bilet
 * asociat și numărul de persaone (index).
 * @author Patricia POPA-MIHAI
 */
public abstract class Passenger {
    private String id;
    private ArrayList<Person> person;
    private Ticket ticket;
    private int index = 0;

    Passenger(String id, Ticket ticket) {
        this.id = id;
        person = new ArrayList<Person>();
        this.index = 0;
        this.ticket = ticket;
    }

    int getPriority() {
        int priority = 0;
        for(int i = 0; i < this.index; i++) {
            priority += this.person.get(i).getPersonPriority();
            priority += this.ticket.getEmbarkPriority();
            priority += this.ticket.getTicketPriority();
        }
        return priority;
    }
    String getID() {
        return this.id;
    }
    String getFirstPerson() {
        return this.person.get(0).getName();     
    }
    String getFirstPersonName() {
        return this.person.get(0).getName();
    }

    boolean isPassengerEmpty() {
        if(this.index == 0) return true;
        else return false;
    }

    void addPerson(String name, int age, boolean specialNeeds) {
        Person p = new Person(name, age, specialNeeds);
        this.person.add(p); 
        this.index++;
    }

    void removePerson(String name) {
        for(int i = 0; i < this.index; i++) {
            if(this.person.get(i).getName().equals(name)) {
                this.person.remove(i);
                this.index--;
            }
        }
    }
}