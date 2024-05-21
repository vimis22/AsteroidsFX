package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {

    /**
     * This method processes the movement of the entities inside the map.
     *
     * @param gameData  The gameData adds boundaries to the world.
     * @param world     The world adds the entity to the map.
     */
    void process(GameData gameData, World world);
}
