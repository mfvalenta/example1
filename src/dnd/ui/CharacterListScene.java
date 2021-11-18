package dnd.ui;

import dnd.Character;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CharacterListScene {

    private CharacterListPanel panel;
    private Scene scene;

    private AddListener addListener;

    public interface AddListener {
        public void addClicked();
    }

    public CharacterListScene(int width, int height) {
        panel = new CharacterListPanel();

        VBox layout = new VBox();
        layout.getChildren().add(panel.getPane());

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> handleAdd(event));
        layout.getChildren().add(addButton);

        scene = new Scene(layout, width, height);
    }

    private Object handleAdd(ActionEvent event) {
        if (addListener != null) {
            addListener.addClicked();
        }
        return null;
    }

    public Scene getScene() {
        return scene;
    }

    public void onAdd(AddListener listener) {
        this.addListener = listener;
    }

    public ObservableList<Character> getCharacters() {
        return panel.getCharacters();
    }
}
