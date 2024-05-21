package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.commonasteroids.SplitAsteroids;
import dk.sdu.mmmi.cbse.asteroids.AsteroidsPlugin;

import java.util.Random;

public class AsteroidsSplitImpl implements SplitAsteroids {

    /**
     * This method ensures creation of multiple splitAsteroids, when shot.
     *
     * @param entity    The entity defines the entity, that is created.
     * @param world     The world adds the entity to the map.
     * @param gameData  The gameData adds boundaries to the world.
     */
    @Override
    public void createSplitAsteroid(Entity entity, World world, GameData gameData){
        Random random = new Random();
        for(int i = 0; i< random.nextInt(0,5); i++){
            AsteroidsPlugin splittedasteroid = new AsteroidsPlugin();
            world.addEntity(splittedasteroid.createAsteroids(gameData,entity));
        }
    }

}
