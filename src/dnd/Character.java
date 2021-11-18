package dnd;

import dnd.races.Race;

/**
 * This class represents a D & D character
 */
public class Character {
    private String name;
    private int strength, dexterity, intelligence, wisdom, constitution, charisma;
    private Race race;

    public Character(String name, Race race) {
        this.name = name;
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getStrength() {
        return strength + race.getStrengthModifier();
    }

    public int getDexterity() {
        return dexterity + race.getDexterityModifier();
    }

    public int getIntelligence() {
        return intelligence + race.getIntelligenceModifier();
    }

    public int getWisdom() {
        return wisdom + race.getWisdomModifier();
    }

    public int getConstitution() {
        return constitution + race.getConstitutionModifier();
    }

    public int getCharisma() {
        return charisma + race.getCharismaModifier();
    }

    public void generateAttributes() {
        this.strength = generateAttribute();
        this.dexterity = generateAttribute();
        this.intelligence = generateAttribute();
        this.wisdom = generateAttribute();
        this.constitution = generateAttribute();
        this.charisma = generateAttribute();
    }

    private int generateAttribute() {
        // We should roll 3 6 sided dice
        Die d = new Die(6);
        int[] rolls = new int[4];
        int min = 6;
        int sum = 0;
        for (int i = 0; i < rolls.length; i++) {
            rolls[i] = d.roll();
            if (rolls[i] < min) {
                min = rolls[i];
            }
            sum += rolls[i];
        }
        return sum - min;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Name: ");
        buffer.append(this.name);
        buffer.append('\n');
        buffer.append("Race: ");
        buffer.append(this.race.getName());
        buffer.append('\n');
        buffer.append("Strength: ");
        buffer.append(this.strength);
        buffer.append('\n');
        buffer.append("Dexterity: ");
        buffer.append(this.dexterity);
        buffer.append('\n');
        buffer.append("Intelligence: ");
        buffer.append(this.intelligence);
        buffer.append('\n');
        buffer.append("Wisdom: ");
        buffer.append(this.wisdom);
        buffer.append('\n');
        buffer.append("Constitution: ");
        buffer.append(this.constitution);
        buffer.append('\n');
        buffer.append("Charisma: ");
        buffer.append(this.charisma);
        buffer.append('\n');
        return buffer.toString();
    }

    public boolean allLessThan70() {
        return (this.strength + this.dexterity + this.intelligence + this.wisdom + this.constitution + this.charisma < 70);
	}

	public boolean oneLessThan6() {
        return (this.strength < 6 || 
                this.dexterity  < 6 || 
                this.intelligence  < 6 || 
                this.wisdom < 6 || 
                this.constitution < 6 || 
                this.charisma < 6);
	}
}
