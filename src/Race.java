public class Race {
    public static Race[] races = new Race[] {
        new Race("Human"),
        new Race("Elf"),
        new Race("Dwarf")
    };
    
    private String name;

    public Race(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
