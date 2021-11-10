import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String createAnotherCharacter;
        do {
            System.out.println("Welcome to the D & D character generator!");

            System.out.println("What is the name of your character?");
            String name = input.readLine();
            if (name.trim().length() == 0) {
                break;
            }

            Character c = new Character(name);
            c.generateAttributes();
            System.out.println("Here is your character");
            System.out.println(c.toString());

            System.out.println("Would you like to generate a character?");
            createAnotherCharacter = input.readLine();

        } while (createAnotherCharacter.equalsIgnoreCase("yes"));
    }
}
