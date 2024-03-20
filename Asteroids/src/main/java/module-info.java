import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroids {
    requires Common;
    requires CommonAsteroids;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroids.AsteroidsPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroids.AsteroidsControlSystem;
    requires javafx.graphics;
}