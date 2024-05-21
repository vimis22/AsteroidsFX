package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import javafx.scene.paint.Color;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    /**
     * This method processes the movement of the bullets inside the map.
     *
     * @param gameData  The gameData adds boundaries to the world.
     * @param world     The world adds the entity to the map.
     */
    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            bullet.setX(bullet.getX()+Math.cos(Math.toRadians(bullet.getRotation()))*1.5);
            bullet.setY(bullet.getY()+Math.sin(Math.toRadians(bullet.getRotation()))*1.5);
            if(bullet.getX()<=0){
                bullet.setDeath(true);
            }else if (bullet.getX() >= gameData.getDisplayWidth()){
                bullet.setDeath(true);
            }

            if(bullet.getY() <=0){
                bullet.setDeath(true);
            }else if (bullet.getY() >= gameData.getDisplayHeight()){
                bullet.setDeath(true);
            }

//            if(bullet.getX()>=gameData.getDisplayWidth() || bullet.getY()>=gameData.getDisplayHeight()){
//                bullet.setDeath(true);
//            }
        }
    }

    /**
     *This method creates bullets, with a shape.
     *
     * @param shooter   The shooter refereres to the entity, that shoots the bullets.
     * @param gameData  The gameData adds boundaries to the world.
     * @return {@code bullet}   Returns a bullet, upon shooting
     */
    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setColor(shooter.getColor());
        bullet.setRadius(2);
        bullet.setPolygonCoordinates(-2, -2, 2, -2,2,2,-2,2);
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        bullet.setRotation(shooter.getRotation());
        return bullet;
    }

    private void setShape(Entity entity) {

    }

}
