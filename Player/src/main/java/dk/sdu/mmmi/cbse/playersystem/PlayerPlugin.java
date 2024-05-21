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

    /**
     * The start method adds the player to the world.
     *
     * @param gameData  The gameData adds boundaries to the world.
     * @param world     The world adds the entity to the map.
     */
    @Override
    public void start(GameData gameData, World world) {
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    /**
     * This method defines the shape of the player.
     *
     * @param gameData  The gameData add boundaries to the world.
     * @return {@code playerShip} The asteroidsShip are the created asteroids.
     */
    private Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new Player();
        playerShip.setRadius(20);
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

    /**
     * This method removes the player, when the game stops.
     *
     * @param gameData  The gameData adds boundaries to the world.
     * @param world     The world adds the entity to the map.
     */
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }
}
