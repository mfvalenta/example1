package dnd.ui;

import dnd.Character;
import dnd.races.Race;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class CharacterPanelScene {
    
    private CharacterPanel characterPanel;
    private TextArea messageArea;

    private Scene scene;

    private SaveListener saveListener;

    private Character character;
    private boolean preexisting;

    public interface SaveListener {
        public void save(Character c, boolean preexisting);
    }
    
    public CharacterPanelScene(int width, int height) {
        characterPanel = new CharacterPanel();

        Button b = new Button("Roll!");
        b.setOnAction(event -> handleClick(event));
        characterPanel.getPane().add(b, 0, characterPanel.getRowCount(), 2, 1);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> handleSave(event));
        characterPanel.getPane().add(saveButton, 0, characterPanel.getRowCount() + 1, 2, 1);

        messageArea = new TextArea();
        characterPanel.getPane().add(messageArea, 0, characterPanel.getRowCount() + 2, 2, 1);

        scene = new Scene(characterPanel.getPane(), width, height);
    }

    private Object handleSave(ActionEvent event) {
        if (character != null && saveListener != null) {
            // Update the name and race to make sure they get updated
            character.setName(characterPanel.getModel().getNameProperty().getValue());
            character.setRace(characterPanel.getModel().getRaceProperty().getValue());
            saveListener.save(character, preexisting);
        }
        return null;
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

        if (character == null) {
            character = new Character(name, race);
        } else {
            character.setName(name);
            character.setRace(race);
        }
        character.generateAttributes();

        updateCharacterAttributes(character);

        if (character.allLessThan70() || character.oneLessThan6()) {
            messageArea.setText("Your roll sucked. You should roll again");
        } else {
            messageArea.setText("");
        }

        return null;
    }

    private void updateCharacterAttributes(Character c) {
        characterPanel.getModel().getAttributeProperty(CharacterModel.STRENGTH).setValue(Integer.toString(c.getStrength()));
        characterPanel.getModel().getAttributeProperty(CharacterModel.DEXTERITY).setValue(Integer.toString(c.getDexterity()));
        characterPanel.getModel().getAttributeProperty(CharacterModel.INTELLIGENCE).setValue(Integer.toString(c.getIntelligence()));
        characterPanel.getModel().getAttributeProperty(CharacterModel.WISDOM).setValue(Integer.toString(c.getWisdom()));
        characterPanel.getModel().getAttributeProperty(CharacterModel.CHARISM).setValue(Integer.toString(c.getCharisma()));
        characterPanel.getModel().getAttributeProperty(CharacterModel.CONSTITUTION).setValue(Integer.toString(c.getConstitution()));
    }

    public void reset() {
        preexisting = false;
        character = null;
        characterPanel.getModel().reset();
    }

    public void setCharacter(Character c) {
        preexisting = true;
        this.character = c;
        characterPanel.getModel().updateFor(c);
    }

    public Scene getScene() {
        return scene;
    }

	public void onSave(SaveListener listener) {
        this.saveListener = listener;
	}
}
