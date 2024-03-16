package dk.sdu.mmmi.cbse.collisions;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class Collisions implements IPostEntityProcessingService{
    @Override
    public void process(GameData gameData, World world) {
        for(Entity entity1 : world.getEntities()){
            for(Entity entity2 : world.getEntities()){
                if(entity1.getID().equals(entity2.getID())){
                    continue;
                }else if(this.collides(entity1,entity2)){
                    world.removeEntity(entity1);
                    world.removeEntity(entity2);
                }
            }
        }
    }

    public Boolean collides(Entity entity1, Entity entity2){
        double direction_x = (double) entity1.getX() - (double) entity2.getX();
        double direction_y = (double) entity1.getY() - (double) entity2.getY();
        double distance_of_xy = (double) Math.sqrt(direction_x*direction_x+direction_y*direction_y);
        return distance_of_xy < (entity1.getRadius() + entity2.getRadius());
    }

}
