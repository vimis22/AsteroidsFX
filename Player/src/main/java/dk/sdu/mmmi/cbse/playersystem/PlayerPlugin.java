package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    private boolean death;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {

        Entity playerShip = new Player();
        playerShip.setPolygonCoordinates(
                -20,0,
                -10,-20,
                20,0,
                -10,20);
        playerShip.setX(gameData.getDisplayHeight()/2);
        playerShip.setY(gameData.getDisplayWidth()/2);
        playerShip.setRotation(Math.random());
        playerShip.setColor(Color.BLUEVIOLET);
        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }
}

/*0,30,
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
        10,20*/
