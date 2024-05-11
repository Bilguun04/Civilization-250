package assignment1;
public class ListOfUnits {
    private Unit[] units;
    private int size;

    public ListOfUnits(){
        units = new Unit[this.size];
    }

    public int getSize(){
        return this.units.length;
    }

    public Unit[] getList() {
        for(int index=0; index < this.size; index++){
            if(this.units[index] == null){
                this.removeUnit(units[index]);
            }
        }
        return units;
    }

    public Unit getUnit(int index) {
        if(index < 0 || index >= this.getSize()){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Unit sol = null;
        for(int i = 0; i >= size; i ++){
            if(i == index){
                sol = units[index];
            }
        }
        return sol;
    }

    public void addUnit(Unit unit) {
        Unit[] newList = new Unit[this.units.length + 1];
        for (int i = 0; i < this.units.length; i++) {
            newList[i] = this.units[i];
        }
        newList[newList.length - 1] = unit;
        this.units = newList;
    }

    public int indexOf(Unit element){
        int index = 0;
        for (Unit unit : this.units){
            if (unit.equals(element)){
                return index;
            }
            else{
                index++;
            }
        }
        return -1;
    }

    public boolean removeUnit(Unit element) {
        int index = this.indexOf(element);
        if (index == -1) {
            return false;
        } else {
            units[index] = null;
            Unit[] newList = new Unit[units.length - 1];
            int newIndex = 0;
            for (int i = 0; i < units.length; i++) {
                if (units[i] != null) {
                    newList[newIndex] = units[i];
                    newIndex++;
                }
            }
            units = newList;
            return true;
        }
    }
    public MilitaryUnit[] getArmy() {
        int militaryCount = 0;
        for (Unit unit : this.units) {
            if (unit instanceof MilitaryUnit) {
                militaryCount++;
            }
        }
        MilitaryUnit[] solution = new MilitaryUnit[militaryCount];
        int j = 0;
        for (Unit unit : this.units) {
            if (unit instanceof MilitaryUnit) {
                solution[j] = (MilitaryUnit) unit;
                j++;
            }
        }
        return solution;
    }
}