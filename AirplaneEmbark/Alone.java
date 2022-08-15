package AirplaneEmbark;

/**
  * Clasa reprezintă tipul de pasager singur și extinde
  * clasa abstractă Passenger.
  * @author Patricia POPA-MIHAI
  */
public class Alone extends Passenger{
    static private int alonePriority = 0;

    Alone(String id, Ticket ticket, String name, int age, boolean specialNeeds) {
        super(id, ticket);
        super.addPerson(name, age, specialNeeds);
    }

    int getPriority() {
        int priority = super.getPriority();
        priority += Alone.alonePriority;
        return priority;
    }    
}