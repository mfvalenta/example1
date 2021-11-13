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

    public Race getRace() {
        return race;
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
        if (race.getName().equals("Elf")) {
            this.strength += 1;
            this.dexterity += 1;
            this.intelligence += 1;
            this.wisdom += 1;
            this.constitution += 1;
            this.charisma += 1;

        } else if (race.getName().equals("Elf")) {
            this.dexterity += 2;
        } else if (race.getName().equals("Dwarf")) {
            this.constitution += 2;
        }    
    }

    private int generateAttribute() {
        // We should roll 3 6 sided dice
        Die d = new Die(6);
        return d.roll() + d.roll() + d.roll();
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
}
