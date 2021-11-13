package dnd.races;

public class Halfling extends Race {

    public Halfling() {
        super("Halfling");
    }
    
    @Override
    public int getDexterityModifier() {
        return 2;
    }
}
