package dk.sdu.mmmi.cbse.collisions;

import dk.sdu.mmmi.cbse.common.data.Entity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionsTest {

    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach is executed");
    }

    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach is executed");
    }

    @Test
    void process() {
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        if(entity1==entity2 || entity1.getColor() == entity2.getColor()){
            System.out.printf("Collision has happened");
        }else{
            System.out.println("Collision has not happened");
        }
    }

    @Test
    void collides() {
        System.out.println("The collision method has been executed.");
    }
}