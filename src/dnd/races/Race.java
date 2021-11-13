package dnd.races;

public class Race {
    public static Race[] races = new Race[] {
        new Human(),
        new Elf(),
        new Dwarf(),
        new Halfling()
    };
    
    private String name;

    public Race(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getStrengthModifier() {
        return 0;
    }

    public int getDexterityModifier() {
        return 0;
    }

    public int getIntelligenceModifier() {
        return 0;
    }

    public int getWisdomModifier() {
        return 0;
    }

    public int getConstitutionModifier() {
        return 0;
    }

    public int getCharismaModifier() {
        return 0;
    }
}
