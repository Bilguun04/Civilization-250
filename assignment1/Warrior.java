package assignment1;
public class Warrior extends MilitaryUnit{
    public Warrior(Tile position, double HP, String faction){
        super(position, HP, 1, faction, 20.0, 1, 25);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}