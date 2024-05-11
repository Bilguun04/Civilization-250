import assignment1.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class MiniTesterJUnit { }

class SyntaxTest {
    @Test
    @Tag("score:0")
    @DisplayName("Test if the expected class names exist")
    void testClassNames() {
        String[] expectedClassNames = {"Archer", "Worker", "Settler", "Tile", "Unit", "Warrior", "MilitaryUnit", "ListOfUnits"};
        boolean allClassesExist = true;
        for (String className : expectedClassNames) {
            try {
                Class.forName("assignment1." + className);
            } catch (ClassNotFoundException e) {
                System.out.println("Missing class: " + className);
                allClassesExist = false;
            }
        }
        // Print that all classes exist
        assertTrue(allClassesExist, "Not all expected classes exist..");
        System.out.println("All expected class names exist..");

    }

    @Test
    @Tag("score:0")
    @DisplayName("Test if Unit and MilitaryUnit are abstract")
    void testAbstractClasses() {
        // Test if Unit and MilitaryUnit are abstract
        assertTrue(Modifier.isAbstract(Unit.class.getModifiers()), "Unit should be an abstract class..");
        assertTrue(Modifier.isAbstract(MilitaryUnit.class.getModifiers()), "MilitaryUnit should be an abstract class..");
    }

    @Test
    @Tag("score:0")
    @DisplayName("Testing inheritance for Unit and MilitaryUnit")
    void testInheritance() {
        boolean allInheritanceCorrect = true;
        allInheritanceCorrect &= isSubclass(Settler.class, Unit.class);
        allInheritanceCorrect &= isSubclass(Worker.class, Unit.class);
        allInheritanceCorrect &= isSubclass(MilitaryUnit.class, Unit.class);
        allInheritanceCorrect &= isSubclass(Archer.class, MilitaryUnit.class);
        allInheritanceCorrect &= isSubclass(Warrior.class, MilitaryUnit.class);

        assertTrue(allInheritanceCorrect, "Not all inheritances are correct..");
    }

    @Test
    @Tag("score:0")
    @DisplayName("Test if all expected classes have a constructor")
    void testIfAllClassesHaveAConstructor() {
        String[] expectedClassNames = {"Archer", "Worker", "Settler", "Tile", "Unit", "Warrior", "MilitaryUnit"};
        // Test if all classes have a constructor
        boolean allClassesHaveAConstructor = true;
        for (String className : expectedClassNames) {
            try {
                Constructor<?> constructor = Class.forName("assignment1." + className).getDeclaredConstructors()[0];
                if (constructor.getParameterCount() == 0) {
                    System.out.println("Missing constructor for class: " + className);
                    allClassesHaveAConstructor = false;
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Missing class: " + className);
                allClassesHaveAConstructor = false;
            }
        }
        // Print that all classes have a constructor
        assertTrue(allClassesHaveAConstructor, "Not all classes have a constructor..");
        System.out.println("All classes have a constructor..");
    }

    @Test
    @Tag("score:0")
    @DisplayName("Test expected method names for each class")
    void testMethodNamesForClasses() throws ClassNotFoundException {
        boolean allMethodsExist = true;
        //For Unit
        allMethodsExist &= testMethodNamesForClass("Unit", "getPosition", "getHP", "getFaction", "moveTo", "receiveDamage", "equals");
        //For Settler
        allMethodsExist &= testMethodNamesForClass("Settler", "takeAction", "equals");
        //For Worker
        allMethodsExist &= testMethodNamesForClass("Worker", "takeAction", "equals");
        //For MilitaryUnit
        allMethodsExist &= testMethodNamesForClass("MilitaryUnit", "takeAction", "receiveDamage");
        //For Warrior
        allMethodsExist &= testMethodNamesForClass("Warrior",  "equals");
        //For Archer
        allMethodsExist &= testMethodNamesForClass("Archer", "takeAction", "equals");
        //For Tile
        allMethodsExist &= testMethodNamesForClass("Tile", "getX", "getY", "isCity", "isImproved", "buildCity", "buildImprovement", "addUnit", "removeUnit", "selectWeakEnemy", "getDistance");
        //For ListOfUnits
        allMethodsExist &= testMethodNamesForClass("ListOfUnits", "getSize", "getList", "getUnit", "addUnit","indexOf", "removeUnit", "getArmy");

        assertTrue(allMethodsExist, "Not all expected methods exist..");
        System.out.println("All expected method names exist..");
    }


    @Test
    @Tag("score:0")
    @DisplayName("Test that Unit Class has abstract method takeAction")
    void testAbstractMethod() {
        // Test that Unit Class has abstract method takeAction
        boolean hasAbstractMethodTakeAction = false;
        for (Method method : Unit.class.getDeclaredMethods()) {
            if(method.getName().equals("takeAction") && Modifier.isAbstract(method.getModifiers())) {
                hasAbstractMethodTakeAction = true;
                break;
            }
        }
        assertTrue(hasAbstractMethodTakeAction, "Unit should have an abstract method takeAction..");
    }

    @Test
    @Tag("score:0")
    @DisplayName("Test that Tile class has a static method getDistance")
    void testStaticMethod() {
        boolean hasStaticMethodGetDistance = false;
        for (Method method : Tile.class.getDeclaredMethods()) {
            if(method.getName().equals("getDistance") && Modifier.isStatic(method.getModifiers())) {
                hasStaticMethodGetDistance = true;
                break;
            }
        }
        assertTrue(hasStaticMethodGetDistance, "Tile class should have a static method getDistance..");
    }

    @Test
    @Tag("score:0")
    @DisplayName("Test if any expected declared fields are missing")
    void testDeclaredFields() {
        boolean allFieldsExist = true;
        //For Unit
        allFieldsExist &= checkFieldsForClass("Unit", Tile.class, double.class, int.class, String.class);
        //For Worker
        allFieldsExist &= checkFieldsForClass("Worker", int.class);
        //For MilitaryUnit
        allFieldsExist &= checkFieldsForClass("MilitaryUnit", double.class, int.class, int.class);
        //For Archer
        allFieldsExist &= checkFieldsForClass("Archer", int.class);
        //For Tile
        allFieldsExist &= checkFieldsForClass("Tile",int.class, int.class, boolean.class, boolean.class, ListOfUnits.class);
        //For ListOfUnits
        allFieldsExist &= checkFieldsForClass("ListOfUnits", Unit[].class, int.class);
        // Warrior and Settler have no declared fields

        assertTrue(allFieldsExist, "Not all expected declared fields exist..");
        System.out.println("All expected declared fields exist..");
    }

    private boolean isSubclass(Class<?> childClass, Class<?> parentClass) {
        if(!parentClass.isAssignableFrom(childClass)) {
            System.out.println(childClass.getSimpleName() + " should be a subclass of " + parentClass.getSimpleName());
            return false;
        }
        return true;
    }
    private boolean testMethodNamesForClass(String className, String... expectedMethodNames) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("assignment1." + className);
        Method[] methods = clazz.getDeclaredMethods();
        Map<String, Method> methodMap = new HashMap<>();
        boolean allMethodsExist = true;
        // Add all declared methods to a map for quick lookup
        for (Method method : methods) {
            methodMap.put(method.getName(), method);
        }

        // Check if each expected method name exists and print those that are missing
        for (String methodName : expectedMethodNames) {
            if (!methodMap.containsKey(methodName)) {
                System.out.println("Missing expected method name in class " + className + ": " + methodName);
                allMethodsExist = false;
            }
        }
        return allMethodsExist;
    }
    private boolean checkFieldsForClass(String className, Class<?>... expectedFieldTypes) {
        boolean classFieldsExist = true;
        try {
            Class<?> clazz = Class.forName("assignment1." + className);
            Field[] fields = clazz.getDeclaredFields();

            Set<Class<?>> expectedTypesSet = new HashSet<>();
            for (Class<?> expectedFieldType : expectedFieldTypes) {
                expectedTypesSet.add(expectedFieldType);
            }

            for (Field field : fields) {
                if (expectedTypesSet.contains(field.getType())) {
                    expectedTypesSet.remove(field.getType());
                }
            }
            if(!expectedTypesSet.isEmpty()) {
                System.out.println("Missing expected declared field(s) in class " + className + ": " + expectedTypesSet);
                classFieldsExist = false;
            }
        } catch (ClassNotFoundException e) {
            // Handle class not found exception here if needed
            return false;
        }
        return classFieldsExist;
    }
}
class UnitTest { // Weight: 25%

    @Test
    @Tag("score:10")
    @DisplayName("Basic exposed tests for methods in Unit Class")
    void testMethodsUnitClass() {
        Tile t1 = new Tile(0, 0);
        Unit unit = new Unit(t1, 50, 2, "Greeks") {
            @Override
            public void takeAction(Tile tile) {

            }
        };
        assertEquals(unit.getPosition(), t1, "Unit getPosition() method did not work properly");
        assertEquals(unit.getHP(), 50, "Unit getHP() method did not work properly");
        assertEquals(unit.getFaction(), "Greeks", "Unit getFaction() method did not work properly");
        unit.receiveDamage(30);
        assertEquals(20.0, unit.getHP(), "Unit receiveDamage() method did not work properly");

        Tile t2 = new Tile(1, 1);
        assertTrue(unit.moveTo(t2), "Unit moveTo() method did not work properly");
    }


}

class TileTest { // Weight: 20%
    @Test
    @Tag("score:8")
    @DisplayName("Basic exposed tests for methods in Tile class")
    void testMethodsTileClass() {
        Tile t1 = new Tile(0, 1);
        assertEquals(0, t1.getX(), "Tile getX() method did not work properly");
        assertEquals(1, t1.getY(), "Tile getY() method did not work properly");
        assertFalse(t1.isCity(), "Tile isCity() method did not work properly");
        assertFalse(t1.isImproved(), "Tile isImproved() method did not work properly");
        t1.buildCity();
        assertTrue(t1.isCity(), "Tile buildCity() method did not work properly");
        t1.buildImprovement();
        assertTrue(t1.isImproved(), "Tile buildImprovement() method did not work properly");
    }
}

class ListOfUnitsTest { // Weight: 15%

    @Test
    @Tag("score:6")
    @DisplayName("Basic exposed tests for methods in ListOfUnits class")
    void testMethodsLOUClass() {
        ListOfUnits lou = new ListOfUnits();
        Tile t1 = new Tile(0, 0);
        Unit unit = new Unit(t1, 50, 2, "Greeks") {
            @Override
            public void takeAction(Tile tile) {

            }
        };
        assertEquals(0, lou.getSize(), "ListOfUnits getSize() method did not work properly");
        assertEquals(0, lou.getList().length, "ListOfUnits getList() method did not work properly");
        lou.addUnit(unit);
        assertEquals(1, lou.getSize(), "ListOfUnits getSize() method did not work properly");
        assertEquals(1, lou.getList().length, "ListOfUnits getList() method did not work properly");
        assertNotNull(lou.getUnit(0), "ListOfUnits getUnit() method did not work properly");
        assertEquals(0, lou.indexOf(lou.getUnit(0)), "ListOfUnits indexOf() method did not work properly");
        assertTrue(lou.removeUnit(unit), "ListOfUnits removeUnit() method did not work properly");
        assertEquals(0, lou.getSize(), "ListOfUnits getSize() method did not work properly");
        MilitaryUnit mUnit = new Archer(t1, 50, "Greeks");
        //Check that the military unit is inside
        lou.addUnit(mUnit);
        MilitaryUnit[] army = lou.getArmy();
        assertEquals(1, army.length, "ListOfUnits getArmy() method did not work properly");
        assertEquals(mUnit, army[0], "ListOfUnits getArmy() method did not work properly");

    }

}

class SettlerTest { // Weight: 10%

    @Test
    @Tag("score:1")
    @DisplayName("Test that equals methods of each class returns false as expected when checked with a different class")
    void testMethodEqualsFalseSettler() {
        Tile t1 = new Tile(0, 0);
        Worker worker = new Worker(t1, 50, "Greeks");
        Settler settler = new Settler(t1, 50, "Greeks");
        Unit unit = new Unit(t1, 50, 2, "Greeks") {
            @Override
            public void takeAction(Tile tile) {

            }
        };

        assertFalse(settler.equals(unit), "Settler equals() method did not work properly");
        assertFalse(settler.equals(worker), "Settler equals() method did not work properly");
        assertFalse(settler.equals(t1), "Settler equals() method did not work properly");
    }

    @Test
    @Tag("score:3")
    @DisplayName("Basic exposed tests for takeAction methods in Settler")
    void testMethodsSettlerClass() {
        Tile position = new Tile(0, 0);
        Settler settler = new Settler(position, 50, "Greeks");
        settler.takeAction(position);
        ListOfUnits listOfUnits = accessList(position);
        assertEquals(-1,listOfUnits.indexOf(settler), "Settler takeAction() method did not work properly as unit was not removed");
    }

    private ListOfUnits accessList(Tile position) {
        Field field = null;
        Field[] fields = Tile.class.getDeclaredFields();
        for(Field f : fields){
            if(f.getType().equals(ListOfUnits.class)){
                field = f;
                break;
            }
        }
        if (field == null) {
            fail("ListOfUnits field not found in Tile class");
        }
        field.setAccessible(true);
        try {
            return (ListOfUnits) field.get(position);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}

class WorkerTest { // Weight: 10%

    @Test
    @Tag("score:1")
    @DisplayName("Test that equals methods of each class returns false as expected when checked with a different class")
    void testMethodEqualsFalseWorker() {
        Tile t1 = new Tile(0, 0);
        Worker worker = new Worker(t1, 50, "Greeks");
        Settler settler = new Settler(t1, 50, "Greeks");
        Unit unit = new Unit(t1, 50, 2, "Greeks") {
            @Override
            public void takeAction(Tile tile) {

            }
        };

        assertFalse(worker.equals(unit), "Worker equals() method did not work properly");
        assertFalse(worker.equals(settler), "Worker equals() method did not work properly");
        assertFalse(unit.equals(t1), "Worker equals() method did not work properly");
    }

    @Test
    @Tag("score:3")
    @DisplayName("Basic exposed tests for takeAction methods in Worker class")
    void testMethodsWorkerClass() {
        Tile position = new Tile(0, 0);
        Worker worker = new Worker(position, 50, "Greeks");
        worker.takeAction(position);
        ListOfUnits listOfUnits = accessList(position);
        assertEquals(0,listOfUnits.indexOf(worker), "Worker takeAction() method did not work properly as unit was not removed");
    }

    private ListOfUnits accessList(Tile position) {
        Field field = null;
        Field[] fields = Tile.class.getDeclaredFields();
        for(Field f : fields){
            if(f.getType().equals(ListOfUnits.class)){
                field = f;
                break;
            }
        }
        if (field == null) {
            fail("ListOfUnits field not found in Tile class");
        }
        field.setAccessible(true);
        try {
            return (ListOfUnits) field.get(position);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }


}

class MilitaryUnitTest {

    //All methods are accessed in Archer and Warrior classes therefore not assessed in exposed tests

}

class ArcherTest { // Weight: 10%


    @Test
    @Tag("score:1")
    @DisplayName("Test that equals methods of each class returns false as expected when checked with a different class")
    void testMethodEqualsFalseArcher() {
        Tile t1 = new Tile(0, 0);
        Warrior warrior = new Warrior(t1, 50, "Greeks");
        Archer archer = new Archer(t1, 50, "Greeks");
        Unit unit = new Unit(t1, 50, 2, "Greeks") {
            @Override
            public void takeAction(Tile tile) {

            }
        };

        assertFalse(archer.equals(unit), "Archer equals() method did not work properly");
        assertFalse(archer.equals(warrior), "Archer equals() method did not work properly");
        assertFalse(archer.equals(t1), "Archer equals() method did not work properly");
    }
    @Test
    @Tag("score:3")
    @DisplayName("Basic test for takeAction in Archer class as well as receiveDamage in MilitaryUnit class")
    void testMethodsArcherClass() throws IllegalAccessException {
        Archer archer = new Archer(new Tile(0, 0), 50, "Greeks");
        Field numberOfArrows = archer.getClass().getDeclaredFields()[0];
        numberOfArrows.setAccessible(true);
        assertEquals(5, numberOfArrows.get(archer));
        // Test takeAction when numberOfArrows is 0
        archer.takeAction(new Tile(1, 1));
        assertEquals(4, numberOfArrows.get(archer), "Archer takeAction() method did not work properly");
        archer.takeAction(new Tile(1, 1));
        assertEquals(3, numberOfArrows.get(archer), "Archer takeAction() method did not work properly");
        archer.takeAction(new Tile(1, 1));
        assertEquals(2, numberOfArrows.get(archer), "Archer takeAction() method did not work properly");
        archer.takeAction(new Tile(1, 1));
        assertEquals(1, numberOfArrows.get(archer), "Archer takeAction() method did not work properly");
        archer.takeAction(new Tile(1, 1));
        assertEquals(0, numberOfArrows.get(archer), "Archer takeAction() method did not work properly");
        archer.takeAction(new Tile(1, 1));
        assertEquals(5, numberOfArrows.get(archer), "Archer takeAction() method did not work properly");

    }

}

class WarriorTest { // Weight: 10%

    @Test
    @Tag("score:1")
    @DisplayName("Test that equals methods of each class returns false as expected when checked with a different class")
    void testMethodEqualsFalseWarrior() {
        Tile t1 = new Tile(0, 0);
        Warrior warrior = new Warrior(t1, 50, "Greeks");
        Archer archer = new Archer(t1, 50, "Greeks");
        Unit unit = new Unit(t1, 50, 2, "Greeks") {
            @Override
            public void takeAction(Tile tile) {

            }
        };

        assertFalse(warrior.equals(unit), "Warrior equals() method did not work properly");
        assertFalse(warrior.equals(archer), "Warrior equals() method did not work properly");
        assertFalse(warrior.equals(t1), "Warrior equals() method did not work properly");
    }

    @Test
    @Tag("score:3")
    @DisplayName("Test methods for Warrior class")
    void testMethodsWarriorClass() {
        Warrior warrior = new Warrior(new Tile(0, 0), 50, "Greeks");
        warrior.receiveDamage(10);
        assertEquals(42, warrior.getHP(), "Warrior receiveDamage() method did not work properly");

    }

}
