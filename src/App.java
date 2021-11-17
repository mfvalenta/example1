import java.util.ArrayList;
import java.util.List;

import dnd.races.Race;
import dnd.ui.CharacterModel;
import dnd.ui.CharacterPanel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class App extends Application {

    public static List<Character> characters = new ArrayList<>();

    CharacterPanel characterPanel;
    TextArea messageArea;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        characterPanel = new CharacterPanel();

        Button b = new Button("Roll!");
        b.setOnAction(event -> handleClick(event));
        characterPanel.getPane().add(b, 0, characterPanel.getRowCount(), 2, 1);

        messageArea = new TextArea();
        characterPanel.getPane().add(messageArea, 0, characterPanel.getRowCount() + 1, 2, 1);

        stage.setTitle("Character Generator");
        stage.setScene(new Scene(characterPanel.getPane(), 300, 500));
        stage.show();
    }

    private Object handleClick(ActionEvent event) {
        String name = characterPanel.getModel().getNameProperty().getValue();
        if (name.trim().length() == 0) {
            return null;
        }
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        if (!name.equals(characterPanel.getModel().getNameProperty().getValue())) {
            characterPanel.getModel().getNameProperty().setValue(name);
        }

        Race race = characterPanel.getModel().getRaceProperty().getValue();
        if (race == null) {
            return null;
        }

        Character c = new Character(name, race);
        c.generateAttributes();

        updateCharacterAttributes(c);

        if (c.allLessThan70() || c.oneLessThan6()) {
            messageArea.setText("Your roll sucked. You should roll again");
        } else {
            messageArea.setText("");
        }

        return c;
    }

    private void updateCharacterAttributes(Character c) {
        characterPanel.getModel().getAttributeProperty(CharacterModel.STRENGTH).setValue(Integer.toString(c.getStrength()));
        characterPanel.getModel().getAttributeProperty(CharacterModel.DEXTERITY).setValue(Integer.toString(c.getDexterity()));
        characterPanel.getModel().getAttributeProperty(CharacterModel.INTELLIGENCE).setValue(Integer.toString(c.getIntelligence()));
        characterPanel.getModel().getAttributeProperty(CharacterModel.WISDOM).setValue(Integer.toString(c.getWisdom()));
        characterPanel.getModel().getAttributeProperty(CharacterModel.CHARISM).setValue(Integer.toString(c.getCharisma()));
        characterPanel.getModel().getAttributeProperty(CharacterModel.CONSTITUTION).setValue(Integer.toString(c.getConstitution()));
    }

}
