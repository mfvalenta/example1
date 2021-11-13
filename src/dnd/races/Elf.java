package dnd.races;

public class Elf extends Race {

    public Elf() {
        super("Elf");
    }
    
    @Override
    public int getDexterityModifier() {
        return 2;
    }
}
