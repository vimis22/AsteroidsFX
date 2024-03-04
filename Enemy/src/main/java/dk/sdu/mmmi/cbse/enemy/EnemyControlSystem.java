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
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)){
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
            Collection<? extends BulletSPI> bulletSPIs = getBulletSPIs();
            for (BulletSPI bulletSPI : bulletSPIs) {
                //Når processen bliver kørt, så bliver bullet skudt når der kommer ændring i enemy.
                //Et eksempel på forslag kan være, at når der ikke er ændring, så skal enemy skyde også når der er ændring.
                //Men start først med, at finde ud af hvorfor enemy'en skyder når der er ændring i process-metoden.
                Entity bullet = bulletSPI.createBullet(enemy, gameData);
                world.addEntity(bullet);
            }
            //changeX and changeY makes the enemy move forward with the help of getRotation, with cos(x) and sin(y) coordinates.
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
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
