package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

import java.util.Random;

public class AsteroidsPlugin implements IGamePluginService {
    private Entity asteroids;

    public AsteroidsPlugin(){

    }

    @Override
    public void start(GameData gameData, World world) {
        int duplication = 4;
        for (int i =0; i<duplication; i++){
            asteroids = createAsteroids(gameData);
            world.addEntity(asteroids);
        }
    }

    private Entity createAsteroids(GameData gameData){
        Entity asteroidsShip = new Asteroids();
        asteroidsShip.setPolygonCoordinates(
                0,30,
                -10,20,
                -20,20,
                -20,10,
                -30,0,
                -20,-10,
                -20,-20,
                -10,-20,
                0,-30,
                10,-20,
                20,-20,
                20,-10,
                30,0,
                20,10,
                20,20,
                10,20
        );
        Random random = new Random();
        asteroidsShip.setX(random.nextDouble(0,gameData.getDisplayWidth()));
        asteroidsShip.setY(random.nextDouble(0, gameData.getDisplayHeight()));
        asteroidsShip.setRotation(random.nextDouble(0,360));
        asteroidsShip.setColor(Color.GREY);
        return asteroidsShip;
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
