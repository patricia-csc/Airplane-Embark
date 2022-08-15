package AirplaneEmbark;

/**
 * Clasa include toți pasagerii ce urmează sa fie introduși în coada de
 * prioritate.
 * @author Patricia POPA-MIHAI
 */
public class Plane {
    private Passenger passengers[];
    private int index;

    Plane(int size) {
        this.index = 0;
        this.passengers = new Passenger[size];
    }
    
    void addPeople(Passenger p) {
        this.passengers[this.index] = p;
        this.index++;
    }

    boolean searchPlane(String id) {
        for(int i = 0; i < this.index; i++) {
            if(this.passengers[i].getID().equals(id)) return true;
        }
        return false;
    }

    Passenger getPassenger(String id) {
        for(int i = 0; i < this.index; i++) {
            if(this.passengers[i].getID().equals(id)) return passengers[i];
        }
        return null;
    }
}