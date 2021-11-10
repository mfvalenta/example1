/**
 * This class represents a D & D character
 */
public class Character {
    private String name;
    private int strength, dexterity, intelligence, wisdom, constitution, charisma;

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getCharisma() {
        return charisma;
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
        return 18;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Name: ");
        buffer.append(this.name);
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
}
