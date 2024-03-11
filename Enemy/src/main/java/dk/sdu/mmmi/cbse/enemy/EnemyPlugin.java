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

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemy(GameData gameData){
        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(
                0,30,
                -30,0,
               0,-30,
                30,0
        );
        enemyShip.setX(gameData.getDisplayHeight()/2);
        enemyShip.setY(gameData.getDisplayWidth()/2);
        enemyShip.setRotation(Math.random());
        enemyShip.setColor(Color.ORANGE);
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}

        /*0,0,
        0,5,
        5,10,
        10,10,
        15,5,
        15,0,
        10,-5,
        5,-5,
        0,0*/