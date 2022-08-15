package AirplaneEmbark;

/**
 * Clasa reprezintă tipul de pasager grup și extinde
 * clasa abstractă Passenger.
 * @author Patricia POPA-MIHAI
 */
public class Group extends Passenger {
    static private int groupPriority = 5;

    Group(String id, Ticket ticket) {
        super(id, ticket);
    }

    int getPriority() {
        int priority = super.getPriority();
        priority += Group.groupPriority;
        return priority;
    }
}