package assignment1;
public class Worker extends Unit{
    private int num_jobs;

    public Worker(Tile position, double HP, String faction){
        super(position, HP, 2, faction);
        num_jobs = 0;
    }

    public void takeAction(Tile tile) {
        if(tile.getX() == this.getPosition().getX() && tile.getY() == this.getPosition().getY() && !tile.isImproved()) {
            tile.buildImprovement();
            num_jobs += 1;
        }
        if(num_jobs >= 10) {
            tile.removeUnit(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
