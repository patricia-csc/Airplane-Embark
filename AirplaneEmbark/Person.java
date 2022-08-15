package AirplaneEmbark;

/**
 * Clasa reprezintă o persoană individuală (ce are nume, vârstă și
 * nevoi speciale) ce poate fi parte dintr-o familie, grup, sau poate
 * fi un pasager singur.
 * @author Patricia POPA-MIHAi
 */
public class Person {
    private String name;
    private int age;
    private int agePriority;
    private SpecialNeeds specialNeed;
    
    Person(String name, int age, boolean specialNeed) {
        this.name = name;
        this.age = age;
        this.specialNeed = new SpecialNeeds(specialNeed);

        if(this.age < 2) this.agePriority = 20;
        if(this.age < 5 && this.age >= 2) this.agePriority = 10;
        if(this.age < 10 && this.age >= 5) this.agePriority = 5;
        if(this.age < 60 && this.age >= 10) this.agePriority = 0;
        if(this.age >= 60) this.agePriority = 15;
    }

    int getAgePriority() {
        return this.agePriority;
    }
    int getPersonPriority() {
        int personPriority = 0;
        personPriority = this.getAgePriority() + this.specialNeed.getSpecialNeedsPriority();
        return personPriority;
    }
    String getName() {
        return this.name;
    }
}