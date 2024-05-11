package assignment1;
public class Archer extends MilitaryUnit{
    private int num_arrows;

    public Archer(Tile position, double HP, String faction){
        super(position, HP, 2, faction, 15.0, 2, 0);
        num_arrows = 5;
    }

    @Override
    public void takeAction(Tile tile){
        if(num_arrows > 0){
            super.takeAction(tile);
            num_arrows -= 1;
        }
        else{
            num_arrows = 5;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}