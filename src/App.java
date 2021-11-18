
import dnd.ui.CharacterListScene;
import dnd.ui.CharacterPanelScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    CharacterListScene listScene;
    CharacterPanelScene characterScene;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        listScene = new CharacterListScene(300, 500);
        characterScene = new CharacterPanelScene(300, 500);

        listScene.onAdd(() -> {
            characterScene.reset();
            stage.setScene(characterScene.getScene());
        });
        characterScene.onSave(c -> {
            listScene.getCharacters().add(c);
            stage.setScene(listScene.getScene());
        });

        stage.setTitle("Character Generator");
        stage.setScene(listScene.getScene());
        stage.show();
    }
}
