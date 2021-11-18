package dnd;

import java.util.Random;

/**
 * This class represents a die
 */
public class Die {

    private static Random random = new Random();
    
    private int faces;
    
    public Die(int faces) {
        this.faces = faces;
    }

    /**
     * Roll the die and return a number between 1 and the number of faces
     */
    public int roll() {
        return (Math.abs(random.nextInt()) % faces) + 1;
    }
}
