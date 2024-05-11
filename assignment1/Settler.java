package assignment1;
public class Settler extends Unit{
    public Settler(Tile position, double HP, String faction){
        super(position, HP, 2, faction);
    }

    @Override
    public void takeAction(Tile tile){
        if (tile.getX() == this.getPosition().getX() && tile.getY() == this.getPosition().getY() && !tile.isCity()){
            tile.buildCity();
            tile.removeUnit(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}