import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dnd.races.Race;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class App extends Application {

    public static List<Character> characters = new ArrayList<>();

    StringProperty nameProperty;
    ObjectProperty<Race> raceProperty;
    List<StringProperty> attributeProperties;

    public static void main(String[] args) throws Exception {

        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Character Generator");

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));

        int currentRow = 0;
        Label nameLabel = new Label("Name:");
        pane.add(nameLabel, 0, currentRow);

        TextField nameField = new TextField();
        pane.add(nameField, 1, currentRow);

        Label raceLabel = new Label("Race:");
        pane.add(raceLabel, 0, ++currentRow);

        ComboBox<Race> racePicker = new ComboBox<>();
        for (Race race : Race.races) {
            racePicker.getItems().add(race);
        }
        racePicker.setConverter(new StringConverter<Race>(){
            @Override
            public Race fromString(String raceName) {
                for (Race race : Race.races) {
                    if (race.getName().equalsIgnoreCase(raceName)) {
                        return race;
                    }
                }
                return null;
            }
            @Override
            public String toString(Race race) {
                if (race == null) {
                    return null;
                }
                return race.getName();
            }
            
        });
        HBox racePickerBox = new HBox(racePicker);
        pane.add(racePickerBox, 1, currentRow);

        Button b = new Button("Generate!");
        b.setOnAction(event -> handleClick(event));
        pane.add(b, 0, ++currentRow, 2, 1);

        String[] labels = {
            "Strength", 
            "Dexterity",
            "Inteligence",
            "Wisdom",
            "Charisma",
            "Constitution"
        };

        attributeProperties = new ArrayList<>();
        for (String string : labels) {
            Label attributeLabel = new Label(string + ":");
            pane.add(attributeLabel, 0, ++currentRow);
            TextField attributeField = new TextField();
            attributeField.setEditable(false);
            pane.add(attributeField, 1, currentRow);
            attributeProperties.add(attributeField.textProperty());
        }

        stage.setScene(new Scene(pane, 300, 500));

        nameProperty = nameField.textProperty();
        raceProperty = racePicker.valueProperty();

        raceProperty.setValue(Race.races[0]);

        stage.show();
    }

    private Object handleClick(ActionEvent event) {
        String name = nameProperty.getValue();
        if (name.trim().length() == 0) {
            return null;
        }
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        if (!name.equals(nameProperty.getValue())) {
            nameProperty.setValue(name);
        }

        Race race = raceProperty.getValue();
        if (race == null) {
            return null;
        }

        Character c = new Character(name, race);
        c.generateAttributes();

        attributeProperties.get(0).setValue(Integer.toString(c.getStrength()));
        attributeProperties.get(1).setValue(Integer.toString(c.getDexterity()));
        attributeProperties.get(2).setValue(Integer.toString(c.getIntelligence()));
        attributeProperties.get(3).setValue(Integer.toString(c.getWisdom()));
        attributeProperties.get(4).setValue(Integer.toString(c.getCharisma()));
        attributeProperties.get(5).setValue(Integer.toString(c.getConstitution()));

        return c;
    }

    private void generate() throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to the D & D character generator!");

        String createAnotherCharacter;
        do {

            System.out.println("What is the name of your character number " + (characters.size() + 1) + "?");
            String name = input.readLine();
            if (name.trim().length() == 0) {
                break;
            }
            name = name.substring(0, 1).toUpperCase() + name.substring(1);

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
