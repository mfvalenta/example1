import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static List<Character> characters = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to the D & D character generator!");

        String createAnotherCharacter;
        do {

            System.out.println("What is the name of your character nummber " + (characters.size() + 1) + "?");
            String name = input.readLine();
            if (name.trim().length() == 0) {
                break;
            }

            Character c = new Character(name);
            c.generateAttributes();

            characters.add(c);

            System.out.print(c.getName());
            System.out.println(" has been added.");

            System.out.println("Would you like to generate another character?");
            createAnotherCharacter = input.readLine();

        } while (createAnotherCharacter.equalsIgnoreCase("yes"));
        if (characters.size() == 1) {
            System.out.println("Here is your character");

        } else {
            System.out.println("Here are your characters");
        }
        for (Character character : characters) {
            System.out.println(character.toString());
        }
    }
}
