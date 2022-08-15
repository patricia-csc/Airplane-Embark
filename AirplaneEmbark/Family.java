package AirplaneEmbark;

/**
 * Clasa reprezintă tipul de pasager familie și
 * extinde clasa abstractă Passenger.
 * @author Patricia POPA-MIHAI
 */
public class Family extends Passenger {
    static private int familyPriority = 10;

    Family(String id, Ticket ticket) {
        super(id, ticket);
    }

    int getPriority() {
        int priority = super.getPriority();
        priority += Family.familyPriority;
        return priority;
    }
}