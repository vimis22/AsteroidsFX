package dk.sdu.mmmi.cbse.commonasteroids;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface SplitAsteroids {
    /**
     * This method ensures the creation of splitAsteroids, when shot.
     *
     * @param entity    The entity defines the entity, that is created.
     * @param world     The world adds the entity to the map.
     * @param gameData  The gameData adds boundaries to the world.
     */
    void createSplitAsteroid(Entity entity, World world, GameData gameData);
}
