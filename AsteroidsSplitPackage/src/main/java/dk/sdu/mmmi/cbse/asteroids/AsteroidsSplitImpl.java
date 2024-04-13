package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.commonasteroids.SplitAsteroids;
import dk.sdu.mmmi.cbse.asteroids.AsteroidsPlugin;

import java.util.Random;

public class AsteroidsSplitImpl implements SplitAsteroids {
    @Override
    public void createSplitAsteroid(Entity entity, World world, GameData gameData){
        Random random = new Random();
        for(int i = 0; i< random.nextInt(0,5); i++){
            AsteroidsPlugin splittedasteroid = new AsteroidsPlugin();
            world.addEntity(splittedasteroid.createAsteroids(gameData,entity));
        }
    }

}
