import java.io.IOException;
import java.util.*;

public class Person201Finder {
    public static void main(String[] args) throws IOException {
        Person201 Adezittus = new Person201("Adeildo", "Brown Residence Hall", 2);
        Person201[] people = Person201Utilities.readURL("https://courses.cs.duke.edu/compsci201/spring23/notes/people.txt");

        System.out.println("Searching for any person near " + Adezittus.getName() + "...\n");
        
        System.out.println("This person/these people is/are on the same floor as " + Adezittus.getName() + ":");
        List<Person201> sameFloor = Person201Utilities.sameFloor(people, Adezittus);
        Person201Utilities.printPeople(sameFloor);

        System.out.println("This person/these people is/are on the same building as " + Adezittus.getName() + ":");
        List<Person201> sameBuilding = Person201Utilities.sameBuilding(people, Adezittus);
        Person201Utilities.printPeople(sameBuilding);
    }

}