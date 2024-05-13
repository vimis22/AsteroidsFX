package dk.sdu.mmmi.cbse.collisions;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionsTest {
    Collisions collision;
    GameData gameData;
    World world;

    @BeforeEach
    void setUp() {
        gameData = new GameData();
        world = new World();
        collision = new Collisions();
        System.out.println("@BeforeEach is executed");
    }

//    @AfterEach
//    void tearDown() {
//        System.out.println("@AfterEach is executed");
//    }

    @Test
    void collision_of_two_different_entities() {
        //Vi danner objekter herhenne, som er vilkårlige.
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        //Her sørger vi for,at entities rammer hinanden gennem samme radius.
        entity1.setRadius(20);
        entity2.setRadius(21);
        //Vi har defineret forskellige farver, hvilket betyder at hvis entity med samme farve kolliderer med sig selv.
        //Så fjernes entity ikke.
        entity1.setColor(Color.BLUE);
        entity2.setColor(Color.RED);
        //Vi ønsker, at sætte koordinater sammen for at undersøge om de støder sammen.
        entity1.setX(0);
        entity2.setX(0);
        entity1.setY(0);
        entity2.setY(0);
        //Vi tester om den ikke er død.
        assertFalse(entity1.getDeath(), "The Entity1 is dead, but should be alive");
        assertFalse(entity2.getDeath(), "The Entity2 is dead, but should be alive");
        //Nu testes, at boolean er true efter kollideringen.
        world.addEntity(entity1);
        world.addEntity(entity2);
        collision.process(gameData,world);
        //Nu vil vi gerne tjekke om vores entity har selve døds-status.
        assertTrue(entity1.getDeath(), "The Entity1 is alive, but should be dead");
        assertTrue(entity2.getDeath(), "The Entity2 is alive, but should be dead");
    }

    @Test
    void no_collision_of_two_different_entities(){
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        //Her sørger vi for, at entities ikke rammer hinanden gemme samme radius.
        entity1.setRadius(1);
        entity2.setRadius(1);
        //Her defineret vi forskellige koordinater.
        entity1.setColor(Color.BLUE);
        entity2.setColor(Color.RED);
        //Vi ønsker, at sætte koordinater sammen for at undersøge om de ikke støder sammen.
        entity1.setX(1);
        entity1.setY(1);
        entity2.setX(10);
        entity2.setX(10);
        //Vi tester om den er død.
        assertFalse(entity1.getDeath(), "The Entity1 is dead, but should be alive");
        assertFalse(entity2.getDeath(), "The Entity2 is dead, but should be alive");
        //Nu testes, at boolean er falsk efter kollideringen.
        world.addEntity(entity1);
        world.addEntity(entity2);
        collision.process(gameData,world);
        //Nu vil vi gerne tjekke om vores entity har ikke døds-status.
        assertFalse(entity1.getDeath(), "The Entity1 is dead, but should be alive");
        assertFalse(entity2.getDeath(), "The Entity2 is dead, but should be alive");
    }

    @Test
    void same_color_upon_collision(){
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        //Her sørger vi for, at entities ikke rammer hinanden gemme samme radius.
        entity1.setRadius(1);
        entity2.setRadius(1);
        //Her defineret vi forskellige koordinater.
        entity1.setColor(Color.BLUE);
        entity2.setColor(Color.BLUE);
        //Vi ønsker, at sætte koordinater sammen for at undersøge om de ikke støder sammen.
        entity1.setX(1);
        entity1.setY(1);
        entity2.setX(10);
        entity2.setX(10);
        //Vi tester om den er død.
        assertFalse(entity1.getDeath(), "The Entity1 is dead, but should be alive");
        assertFalse(entity2.getDeath(), "The Entity2 is dead, but should be alive");
        //Nu testes, at boolean er falsk efter kollideringen.
        world.addEntity(entity1);
        world.addEntity(entity2);
        collision.process(gameData,world);
        //Nu vil vi gerne tjekke om vores entity har ikke døds-status.
        assertFalse(entity1.getDeath(), "The Entity1 is dead, but should be alive");
        assertFalse(entity2.getDeath(), "The Entity2 is dead, but should be alive");
    }

    @Test
    void not_same_color_upon_no_collision(){
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        //Her sørger vi for, at entities ikke rammer hinanden gemme samme radius.
        entity1.setRadius(1);
        entity2.setRadius(1);
        //Her defineret vi forskellige koordinater.
        entity1.setColor(Color.RED);
        entity2.setColor(Color.RED);
        //Vi ønsker, at sætte koordinater sammen for at undersøge om de ikke støder sammen.
        entity1.setX(1);
        entity1.setY(1);
        entity2.setX(10);
        entity2.setX(10);
        //Vi tester om den er død.
        assertFalse(entity1.getDeath(), "The Entity1 is dead, but should be alive");
        assertFalse(entity2.getDeath(), "The Entity2 is dead, but should be alive");
        //Nu testes, at boolean er falsk efter kollideringen.
        world.addEntity(entity1);
        world.addEntity(entity2);
        collision.process(gameData,world);
        //Nu vil vi gerne tjekke om vores entity har ikke døds-status.
        assertFalse(entity1.getDeath(), "The Entity1 is dead, but should be alive");
        assertFalse(entity2.getDeath(), "The Entity2 is dead, but should be alive");
    }


}