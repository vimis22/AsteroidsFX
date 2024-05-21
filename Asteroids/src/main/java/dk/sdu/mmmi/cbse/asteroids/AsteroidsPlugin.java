package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonasteroids.Asteroids;
import javafx.scene.paint.Color;

import java.util.Random;

public class AsteroidsPlugin implements IGamePluginService {
    private Entity asteroids;

    public AsteroidsPlugin(){

    }

    /**
     * The start method adds multiple asteroids to the world.
     *
     * @param gameData  The gameData adds boundaries to the world.
     * @param world     The world adds the entity to the map.
     */
    @Override
    public void start(GameData gameData, World world) {
        Random random = new Random();
        int duplication = random.nextInt(0,10);
        for (int i =0; i<duplication; i++){
            asteroids = createAsteroids(gameData,null);
            world.addEntity(asteroids);
        }
    }

    /**
     * This method defines the shape of asteroids.
     *
     * @param gameData  The gameData add boundaries to the world.
     * @param entity    The entity defines the entity, that is created.
     * @return {@code asteroidsShip} The asteroidsShip are the created asteroids.
     */
    public Entity createAsteroids(GameData gameData, Entity entity){
        Entity asteroidsShip = new Asteroids();
        Random random = new Random();
        double size = 0;
        if(entity==null){
            size = random.nextDouble(10,30);
            asteroidsShip.setX(random.nextDouble(0,gameData.getDisplayWidth()));
            asteroidsShip.setY(random.nextDouble(0, gameData.getDisplayHeight()));
        }else{
            size = entity.getSize()/2;
            asteroidsShip.setX(entity.getX());
            asteroidsShip.setY(entity.getY());
        }
        double big = size;
        double medium = (size/3)*2;
        double small = (size/3);
        asteroidsShip.setPolygonCoordinates(
                0,big,
                -small,medium,
                -medium,medium,
                -medium,small,
                -big,0,
                -medium,-small,
                -medium,-medium,
                -small,-medium,
                0,-big,
                small,-medium,
                medium,-medium,
                medium,-small,
                big,0,
                medium,small,
                medium,medium,
                small,medium
        );
        asteroidsShip.setRadius(size);
        asteroidsShip.setRotation(random.nextDouble(0,360));
        asteroidsShip.setColor(Color.GREY);
        return asteroidsShip;
    }

    /**
     * This method removes the asteroids, when the game stops.
     *
     * @param gameData  The gameData adds boundaries to the world.
     * @param world     The world adds the entity to the map.
     */
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroids);
    }
}
