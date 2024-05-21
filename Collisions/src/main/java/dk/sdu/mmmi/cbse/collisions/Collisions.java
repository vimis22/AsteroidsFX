package dk.sdu.mmmi.cbse.collisions;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class Collisions implements IPostEntityProcessingService{

    /**
     * This method processes the movement of the entites inside the map.
     *
     * @param gameData  The gameData adds boundaries to the world.
     * @param world     The world adds the entity to the map.
     */
    @Override
    public void process(GameData gameData, World world) {
        for(Entity entity1 : world.getEntities()){
            for(Entity entity2 : world.getEntities()){
                if(entity1==entity2 || entity1.getColor()==entity2.getColor()){
                    //System.out.println("Not Collided");
                    continue;
                }
                if(this.collides(entity1,entity2)){
                    entity1.setDeath(true);
                    entity2.setDeath(true);
                    System.out.println("Collided");
                }
            }
        }
    }

    /**
     * This method ensures a check on entities, upon collision, based on distance.
     *
     * @param entity1   This is a entity, with a defined radius.
     * @param entity2   This is a entity, with a defined radius.
     * @return {@code collides}     Returns collides, when the distance between two entities is larger than their radius.
     */
    public Boolean collides(Entity entity1, Entity entity2){
        Boolean collides = false;
        double distance = Math.sqrt(((entity2.getX()-entity1.getX())*(entity2.getX()-entity1.getX()))+
                (entity2.getY()-entity1.getY())*(entity2.getY()-entity1.getY()));
        if(entity1.getRadius()>distance || entity2.getRadius()>distance){
            collides = true;
        }
        return collides;
    }
}
