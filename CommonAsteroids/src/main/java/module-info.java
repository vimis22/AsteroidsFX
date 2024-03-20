import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module CommonAsteroids {
    requires Common;
    requires javafx.graphics;
    exports dk.sdu.mmmi.cbse.commonasteroids;
    provides IGamePluginService with dk.sdu.mmmi.cbse.commonasteroids.CommonAsteroids;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.commonasteroids.CommonAsteroids;
}