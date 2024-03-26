package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.commonasteroids.Asteroids;
import dk.sdu.mmmi.cbse.commonasteroids.SplitAsteroids;

public class AsteroidsPostControlSystem implements IPostEntityProcessingService {
    public SplitAsteroids asteroidsSplitter = new AsteroidsSplitImpl();

    @Override
    public void process(GameData gameData, World world) {
        //Kigger efter hvor mange asteroids der er tilbage.
        //Size fortæller, hvor mange elementer der er i et array.
        //Metoden fortæller, at hvis der er ingen asteroider, så skal der dannes flere udefra Random i AsteroidsPlugin.
        if(world.getEntities(Asteroids.class).size() == 0){
            AsteroidsPlugin asteroidsPlugin = new AsteroidsPlugin();
            asteroidsPlugin.start(gameData,world);
        }else{
            for (Entity asteroids: world.getEntities(Asteroids.class) ) {
                asteroidsSplitter.createSplitAsteroid(asteroids,world,gameData);
            }
        }
    }

    public void setAsteroidsSplitter(SplitAsteroids asteroidsSplitter){
        this.asteroidsSplitter = asteroidsSplitter;
    }

    public void removeAsteroidsSplitter(SplitAsteroids asteroidsSplitter){
        this.asteroidsSplitter = null;
    }
}
