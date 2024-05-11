package assignment1;
public abstract class MilitaryUnit extends Unit{
    private double attack_damage;
    private int attack_range;
    private int armor;

    public MilitaryUnit(Tile position, double HP, int range, String faction, double attack_damage, int attack_range, int armor){
        super(position, HP, range, faction);
        this.armor = armor;
        this.attack_damage = attack_damage;
        this.attack_range = attack_range;
    }

    public void takeAction(Tile tile){
        if(Math.abs(tile.getX()-this.getPosition().getX()) > attack_range){
            return;
        }
        Unit weakestEnemy = tile.selectWeakEnemy(this.getFaction());
        if(weakestEnemy != null){
            double damage = attack_damage;
            if(tile.isImproved()){
                damage *= 1.05;
            }
            weakestEnemy.receiveDamage(damage);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public void receiveDamage(double damage){
        double multiplier = 100.0/(100+armor);
        super.receiveDamage(damage * multiplier);
    }
}
