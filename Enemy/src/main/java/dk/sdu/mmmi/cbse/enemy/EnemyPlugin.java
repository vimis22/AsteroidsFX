package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;

    public EnemyPlugin() {

    }

    /**
     * The start method adds multiple enemies to the world.
     *
     * @param gameData  The gameData adds boundaries to the world.
     * @param world     The world adds the entity to the map.
     */
    @Override
    public void start(GameData gameData, World world) {
        Random random = new Random();
        int duplication = random.nextInt(0,10);
        for (int i = 0; i<duplication; i++){
            enemy = createEnemy(gameData);
            world.addEntity(enemy);
        }
    }

    /**
     * This method defines the shape of enemies.
     *
     * @param gameData  The gameData add boundaries to the world.
     * @return {@code enemyShip} The asteroidsShip are the created asteroids.
     */
    private Entity createEnemy(GameData gameData){
        Entity enemyShip = new Enemy();
        enemyShip.setRadius(30);
        enemyShip.setPolygonCoordinates(
                0,30,
                -30,0,
               0,-30,
                30,0
        );
        Random random = new Random();
        enemyShip.setX(random.nextDouble(0, gameData.getDisplayWidth()));
        enemyShip.setY(random.nextDouble(0, gameData.getDisplayHeight()));
        enemyShip.setRotation(random.nextDouble(0,360));
        enemyShip.setColor(Color.ORANGE);
        return enemyShip;
    }

    /**
     * This method removes the enemies, when the game stops.
     *
     * @param gameData  The gameData adds boundaries to the world.
     * @param world     The world adds the entity to the map.
     */
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}

