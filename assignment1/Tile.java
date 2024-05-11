package assignment1;

import java.security.KeyStore;

public class Tile{
    private int x;
    private int y;
    private boolean city;
    private boolean improved;
    private ListOfUnits listOfUnits;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.city = false;
        this.improved = false;
        this.listOfUnits = new ListOfUnits();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCity() {
        return city;
    }

    public boolean isImproved() {
        return improved;
    }

    public void buildCity() {
        city = true;
    }

    public void buildImprovement() {
        improved = true;
    }

    public boolean addUnit(Unit unit) {
        if (unit instanceof MilitaryUnit) {
            MilitaryUnit[] armyList = listOfUnits.getArmy();
            for (Unit u : armyList) {
                if (!u.getFaction().equals(unit.getFaction())) {
                    return false;
                }
            }
        }
        listOfUnits.addUnit(unit);
        return true;
    }

    public boolean removeUnit(Unit unit){
        return listOfUnits.removeUnit(unit);
    }

    public Unit selectWeakEnemy(String faction) {
        Unit weakEnemy = null;
        double lowestHealth = 0;

        for (Unit unit : listOfUnits.getList()) {
            if (unit.getFaction() != null && !unit.getFaction().equals(faction) && unit.getHP() < lowestHealth) {
                weakEnemy = unit;
                lowestHealth = unit.getHP();
            }
        }

        return weakEnemy;
    }

    public static double getDistance(Tile tile1, Tile tile2) {
        double distance;
        distance = Math.sqrt(Math.pow(tile1.x-tile2.x, 2)+Math.pow(tile1.y-tile2.y, 2));
        return distance;
    }
}