package assignment1;
public class ListOfUnits {
    private Unit[] units;
    private int size;

    public ListOfUnits(){
        this.units = new Unit[this.size];
    }

    public int getSize(){
        return this.size;
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

    public void addUnit(Unit unit){
        size = this.getSize();
        int newCapacity = size + 1;
        Unit[] newArray = new Unit[newCapacity];
        if (size > 0){
            for(int i = 1;  i < size; i++){
                newArray[i] = units[i];
            }
        }
        else{
            return;
        }
        units = newArray;
        units[size] = unit;
    }

    public int indexOf(Unit element){
        int sol;
        for(int item = 0; item < this.size; item++){
            if(this.units[item].equals(element)){
                sol = item;
            }
        }
        sol = -1;
        return sol;
    }

    public boolean removeUnit(Unit element) {
        int index = indexOf(element);
        if(index == -1) {
            return false;
        }
        else {
            units[index] = null;
            this.size--;
            return true;
        }
    }
    public MilitaryUnit[] getArmy() {
        int militaryCount = 0;
        for (int i = 0; i < this.size; i++) {
            if (units[i] instanceof MilitaryUnit) {
                militaryCount++;
            }
        }
        MilitaryUnit[] solution = new MilitaryUnit[militaryCount];
        int currentIndex = 0;
        for (int i = 0; i < this.size; i++) {
            if (units[i] instanceof MilitaryUnit) {
                solution[currentIndex++] = (MilitaryUnit) units[i];
            }
        }
        
        return solution;
    }
}
