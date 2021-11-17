package dnd.ui;

import java.util.ArrayList;
import java.util.List;

import dnd.races.Race;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

public class CharacterPanel {

    private CharacterModel model;
    private GridPane pane;

    private int rowCount;
    
    public CharacterPanel() {
        
        pane = new GridPane();
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

        List<StringProperty> attributeProperties = new ArrayList<>();
        for (String string : CharacterModel.ATTRIBUTE_LABELS) {
            Label attributeLabel = new Label(string + ":");
            pane.add(attributeLabel, 0, ++currentRow);
            TextField attributeField = new TextField();
            attributeField.setEditable(false);
            pane.add(attributeField, 1, currentRow);
            attributeProperties.add(attributeField.textProperty());
        }

        racePicker.valueProperty().setValue(Race.races[0]);

        model = new CharacterModel(nameField.textProperty(), racePicker.valueProperty(), attributeProperties.toArray(new StringProperty[attributeProperties.size()]));
        rowCount = currentRow + 1;
    }

    public CharacterModel getModel() {
        return model;
    }

    public GridPane getPane() {
        return pane;
    }

    public int getRowCount() {
        return rowCount;
    }

	public void setMessage(String string) {
	}
}
