package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class AsteroidsControlSystem implements IEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) {
        for(Entity asteroids: world.getEntities(Asteroids.class)){
            double changeX = Math.cos(Math.toRadians(asteroids.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroids.getRotation()));
            asteroids.setX(asteroids.getX() + changeX);
            asteroids.setY(asteroids.getY() + changeY);

            if (asteroids.getX()<0){
                asteroids.setX(gameData.getDisplayWidth());
            }else if(asteroids.getX() >= gameData.getDisplayWidth()){
                asteroids.setX(0);
            }

            if (asteroids.getY() <= 0){
                asteroids.setY(gameData.getDisplayHeight());
            }else if (asteroids.getY() >= gameData.getDisplayHeight()){
                asteroids.setY(0);
            }
        }
    }
}
