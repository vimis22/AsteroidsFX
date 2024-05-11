package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class  PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 5);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 5);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            if (gameData.getKeys().isDown(GameKeys.DOWN)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() - changeX);
                player.setY(player.getY() - changeY);
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE)){
                Collection<? extends BulletSPI> bulletSPIs = getBulletSPIs();
                for (BulletSPI bulletSPI : bulletSPIs) {
                    Entity bullet = bulletSPI.createBullet(player, gameData);
                    world.addEntity(bullet);
                }
            }

            if (player.getX() <= 0) {
                player.setX(gameData.getDisplayWidth());
            }else if (player.getX() >= gameData.getDisplayWidth()) {
                player.setX(0);
            }

            if (player.getY() <= 0) {
                player.setY(gameData.getDisplayHeight());
            }else if (player.getY() >= gameData.getDisplayHeight()) {
                player.setY(0);
            }

        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
