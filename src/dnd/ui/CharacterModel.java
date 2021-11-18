package dnd.ui;

import dnd.Character;
import dnd.races.Race;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class CharacterModel {

    public static final int STRENGTH = 0;
    public static final int DEXTERITY = 1;
    public static final int INTELLIGENCE = 2;
    public static final int WISDOM = 3;
    public static final int CHARISM = 4;
    public static final int CONSTITUTION = 5;

    public static final String[] ATTRIBUTE_LABELS = {
        "Strength", 
        "Dexterity",
        "Intelligence",
        "Wisdom",
        "Charisma",
        "Constitution"
    };

    private StringProperty nameProperty;
    private ObjectProperty<Race> raceProperty;
    private StringProperty[] attributeProperties;

    public CharacterModel(StringProperty nameProperty, ObjectProperty<Race> raceProperty, StringProperty[] attributeProperties) {
        this.nameProperty = nameProperty;
        this.raceProperty = raceProperty;
        this.attributeProperties = attributeProperties;
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public ObjectProperty<Race> getRaceProperty() {
        return raceProperty;
    }

    public StringProperty getAttributeProperty(int attributeIndex) {
        return attributeProperties[attributeIndex];
    }

	public void reset() {
        nameProperty.setValue("");
        raceProperty.setValue(Race.races[0]);
        for (StringProperty prop : attributeProperties) {
            prop.setValue("");
        }
	}

	public void updateFor(Character c) {
        getNameProperty().setValue(c.getName());
        getRaceProperty().setValue(c.getRace());
        getAttributeProperty(CharacterModel.STRENGTH).setValue(Integer.toString(c.getStrength()));
        getAttributeProperty(CharacterModel.DEXTERITY).setValue(Integer.toString(c.getDexterity()));
        getAttributeProperty(CharacterModel.INTELLIGENCE).setValue(Integer.toString(c.getIntelligence()));
        getAttributeProperty(CharacterModel.WISDOM).setValue(Integer.toString(c.getWisdom()));
        getAttributeProperty(CharacterModel.CHARISM).setValue(Integer.toString(c.getCharisma()));
        getAttributeProperty(CharacterModel.CONSTITUTION).setValue(Integer.toString(c.getConstitution()));
	}
    
}
