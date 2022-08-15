package AirplaneEmbark;

/**
 * Clasa reprezintă nevoile speciale ale persoanei (dacă are nevoi speciale
 * și care este valorea priorității acestora).
 * @author Patricia POPA-MIHAI
 */
public class SpecialNeeds {
    private boolean specialNeeds;
    private int specialNeedsPriority;

    SpecialNeeds(boolean specialNeeds) {
        this.specialNeeds = specialNeeds;
        if(this.specialNeeds) specialNeedsPriority = 100;
        else specialNeedsPriority = 0;
    }

    int getSpecialNeedsPriority() {
        return this.specialNeedsPriority;
    }
}