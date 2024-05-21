package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {
    /**
     * This method processes the movement of multiple enemies inside the map.
     *
     * @param gameData  The gameData adds boundaries to the world.
     * @param world     The world adds the entity to the map.
     */
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)){
//            getBulletSPIs().stream().findFirst().ifPresent(
//                    spi -> { world.addEntity(spi.createBullet(enemy,gameData)); }
//            );
            if (Math.random()*1000<1.0){
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> { world.addEntity(spi.createBullet(enemy,gameData)); }
                );
            }
            Random random = new Random();
            //enemy.setRotation(enemy.getRotation() + random.nextDouble(0,360));
            double distance = Math.sqrt(((enemy.getobjectiveX()-enemy.getX())*(enemy.getobjectiveX()-enemy.getX()))+
                    (enemy.getobjectiveY()-enemy.getY())*(enemy.getobjectiveY()-enemy.getY()));
            if(distance < 100){
                enemy.setObjectiveX(random.nextDouble(0,gameData.getDisplayWidth()));
                enemy.setObjectiveY(random.nextDouble(0,gameData.getDisplayHeight()));
            }else{
                double radian_angle = Math.toDegrees(Math.atan2((enemy.getobjectiveY()-enemy.getY()),(enemy.getobjectiveX()-enemy.getX())));
                enemy.setRotation(
                        radian_angle
                );
            }

            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);

            if (enemy.getX() < 0) {
                enemy.setX(gameData.getDisplayWidth());
            }else if (enemy.getX() >= gameData.getDisplayWidth()) {
                enemy.setX(0);
            }

            if (enemy.getY() <= 0) {
                enemy.setY(gameData.getDisplayHeight());
            }else if (enemy.getY() >= gameData.getDisplayHeight()) {
                enemy.setY(0);
            }
        }
    }

    /**
     * This method ensures, that the BulletSPI is implemented in this case, so the functionality can be provided by this module.
     * @return {@BulletSPI} The BulletSPI ensures, that the bullet is loaded at the shooter, so that the shooter is able to shoot.
     */
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
