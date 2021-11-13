package dnd.races;

public class Dwarf extends Race {

    public Dwarf() {
        super("Dwarf");
    }
    
    @Override
    public int getConstitutionModifier() {
        return 2;
    }
}
