import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dnd.races.Race;

public class App {

    public static List<Character> characters = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to the D & D character generator!");

        String createAnotherCharacter;
        do {

            System.out.println("What is the name of your character number " + (characters.size() + 1) + "?");
            String name = input.readLine();
            name = name.substring(0,1).toUpperCase() + name.substring(1);
            if (name.trim().length() == 0) {
                break;
            }

            // Ask for a race
            System.out.println("What is the race of " + name + "?");
            for (int i = 0; i < Race.races.length; i++) { 
                Race r = Race.races[i];
                System.out.println(i + ": " + r.getName());
            }
            System.out.println("Enter the index for the race of " + name);
            String raceIndexString = input.readLine();
            int raceIndex = Integer.parseInt(raceIndexString);

            Character c = new Character(name, Race.races[raceIndex]);
            c.generateAttributes();

            while (c.allLessThan70() || c.oneLessThan6()) {
                System.out.println("Your roll sucked. Do you want to roll again?");
                String again = input.readLine();
                if (again.equalsIgnoreCase("yes")) {
                    c.generateAttributes();
                } else {
                    break;
                }
            }

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
