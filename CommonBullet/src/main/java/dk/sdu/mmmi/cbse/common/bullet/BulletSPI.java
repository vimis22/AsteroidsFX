package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author corfixen
 */
public interface BulletSPI {

    /**
     *This method creates bullets, with a shape.
     *
     * @param e   The "e" refereres to the entity, which shoots with the bullet.
     * @param gameData  The gameData adds boundaries to the world.
     * @return {@code bullet}   Returns a bullet, upon shooting
     */
    Entity createBullet(Entity e, GameData gameData);
}
