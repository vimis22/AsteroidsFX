import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Plugins {
    requires Common;
    exports dk.sdu.mmmi.cbse.plugins;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.plugins.EntityControlSystem;
}