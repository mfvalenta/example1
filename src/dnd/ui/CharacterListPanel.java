package dnd.ui;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import dnd.Character;

public class CharacterListPanel {
    
    private ObservableList<Character> characters;

    private ReadOnlyObjectProperty<Character> selection;

    private Pane pane;

    ListView<Character> list;

    private class CharacterListCell extends ListCell<Character> {
        @Override
        protected void updateItem(Character item, boolean update) {
            super.updateItem(item, update);
            if (item == null) {
                setText(null);
            } else {
                setText(item.getName());
            }
        }
    }

	public CharacterListPanel() {
        list = new ListView<>();
        list.setCellFactory(new Callback<ListView<Character>,ListCell<Character>>(){
            @Override
            public ListCell<Character> call(ListView<Character> arg0) {
                return new CharacterListCell();
            }
        });
        characters = list.itemsProperty().getValue();

        selection = list.getSelectionModel().selectedItemProperty();

        StackPane pane = new StackPane();
        pane.getChildren().add(list);

        this.pane = pane;
    }

    public ObservableList<Character> getCharacters() {
        return characters;
    }

    public ReadOnlyObjectProperty<Character> getSelection() {
        return selection;
    }

    public Pane getPane() {
        return pane;
    }

    public void refreshList() {
        list.refresh();
    }
}
