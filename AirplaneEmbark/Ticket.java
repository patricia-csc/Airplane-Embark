package AirplaneEmbark;

/**
 * Clasa conține tipul de bilet și tipul de îmbarcare (prioritară sau
 * neprioritară) precum și prioritățile acestora.
 * @author Patricia POPA-MIHAI
 */
public class Ticket {
    private String ticket;
    private boolean priorityEmbark;
    private int ticketPriority;
    private int priorityEmbarkPriority;

    Ticket(String ticket, boolean priorityEmbark) {
        this.ticket = ticket;
        this.priorityEmbark = priorityEmbark;
        if(this.ticket.equals("e")) this.ticketPriority = 0;
        if(this.ticket.equals("p")) this.ticketPriority = 20;
        if(this.ticket.equals("b")) this.ticketPriority = 35;
        if(this.priorityEmbark) this.priorityEmbarkPriority = 30;
        else this.priorityEmbarkPriority = 0;
    }

    int getTicketPriority() {
        return this.ticketPriority;
    }

    int getEmbarkPriority() {
        return priorityEmbarkPriority;
    }
}