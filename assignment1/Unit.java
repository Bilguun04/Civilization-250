package assignment1;
public abstract class Unit{
    private Tile position;
    private double HP;
    private int range;
    private String faction;

    public Unit(Tile position, double HP, int range, String faction) {
        this.position = position;
        this.HP = HP;
        this.range = range;
        this.faction = faction;
        if (!position.addUnit(this)) {
            throw new IllegalArgumentException();
        }
    }

    public final Tile getPosition(){
        return position;
    }

    public final double getHP(){
        return HP;
    }

    public final String getFaction(){
        return faction;
    }

    public boolean moveTo(Tile targetTile) {
        int pos_x = targetTile.getX();
        int pos_y = targetTile.getY();

        if(this.position.getX()-pos_x < this.range || this.position.getY()-pos_y < this.range){
            Tile position = new Tile(pos_x, pos_y);
            return true;
        }
        return false;
    }

    public void receiveDamage(double damage) {
        if(damage >= 0) {
            if (position.isCity()) {
                HP -= damage * 0.9;
            }
            else{
                HP -= damage;
            }
        }
        if (HP <= 0){
            position.removeUnit((this));
        }
    }

    public abstract void takeAction(Tile tile);

    public boolean equals(Object obj){
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Unit otherUnit = (Unit) obj;
        return Math.pow((otherUnit.getHP() - HP),2) < 0.001 &&
                this.position.getX() == otherUnit.getPosition().getX() &&
                this.position.getY() == otherUnit.getPosition().getY() &&
                this.faction.equals(otherUnit.faction);
    }
}